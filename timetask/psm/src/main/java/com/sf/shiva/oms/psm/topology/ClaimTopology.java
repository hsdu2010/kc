package com.sf.shiva.oms.psm.topology;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.bolt.ClaimCalBolt;
import com.sf.shiva.oms.psm.bolt.ClaimFilterBolt;
import com.sf.shiva.oms.psm.bolt.PackageStatusSendBolt;
import com.sf.shiva.oms.psm.cfgmanage.ClaimTopologyProperties;
import com.sf.shiva.oms.psm.cfgmanage.SystemProperties;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.enumtype.StreamIdEnum;
import com.sf.shiva.oms.psm.common.enumtype.TopologyNameEnum;
import com.sf.shiva.oms.psm.common.kafka.KafkaSpoutConfig;
import com.sf.shiva.oms.psm.common.kafka.KafkaSpoutFactory;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.spout.ClaimScheme;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

/**
 * 描述：理赔Topology
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月13日      01369521         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369521
 * @since 1.0
 */
public class ClaimTopology {

	private ClaimTopology() {
		super();
	}

	private static final Logger logger = LoggerFactory.getLogger(ClaimTopology.class);

	public static void main(String[] args) throws InvalidTopologyException {
		try {
			SystemProperties systemProperties = SpringContext.getInstance().getBean(SystemProperties.class);// 获取System配置信息
			ClaimTopologyProperties claimTopologyProperties = SpringContext.getInstance().getBean(ClaimTopologyProperties.class);// 获取Topology配置信息
			if (StringUtils.equals(PackageStatusConstant.SYSTEM_ENV_DEV, systemProperties.getSystemEnv())) {
				// "claimTopology" 是topology的id，可在jstorm命令中通过该id来管理topology
				new LocalCluster().submitTopology(TopologyNameEnum.COS_CLAIM.getName(), buildConfig(claimTopologyProperties), buildTopologyBuilder(claimTopologyProperties).createTopology());
			} else {
				StormSubmitter.submitTopology(TopologyNameEnum.COS_CLAIM.getName(), buildConfig(claimTopologyProperties), buildTopologyBuilder(claimTopologyProperties).createTopology());
			}
		} catch (AlreadyAliveException e) {
			logger.error("ClaimTopology error", e);
		}
	}

	/**
	 * 构建TopologyBuilder
	 * 
	 * @param claimTopologyProperties
	 *            配置文件实体
	 * @return
	 * @author 01369521
	 * @date 2017年12月13日
	 */
	private static TopologyBuilder buildTopologyBuilder(ClaimTopologyProperties claimTopologyProperties) {
		TopologyBuilder builder = new TopologyBuilder();
		// setSpout 方法的 第三个参数 3，就是Spout实例数，也是线程数，一个线程执行一个实例
		builder.setSpout("claimCosSpout", KafkaSpoutFactory.create(SpringContext.getInstance().getBean("claimSpoutConfig", KafkaSpoutConfig.class), new ClaimScheme()), claimTopologyProperties.getSpoutThreadNum());
		// setBolt 第三个参数同上；setNumTasks方法原来是设置实例数量，不过在jstorm的后续版本中已不再使用，无效
		builder.setBolt("claimFilterBolt", new ClaimFilterBolt(), claimTopologyProperties.getFilterBoltThreadNum()).localOrShuffleGrouping("claimCosSpout");
		builder.setBolt("claimCalBolt", new ClaimCalBolt(), claimTopologyProperties.getCalBoltThreadNum()).localOrShuffleGrouping("claimFilterBolt");
		// shuffleGrouping 随机分组，把Tuple 随机分发到每个同名bolt实例中
		builder.setBolt("packageStatusSendBolt", new PackageStatusSendBolt(), claimTopologyProperties.getPackageStatusSendBoltThreadNum()).localOrShuffleGrouping("claimCalBolt", StreamIdEnum.PACKAGE_STATUS_SEND.getStreamId());
		return builder;
	}

	/**
	 * 构建配置对象
	 * 
	 * @param topologyDisConf
	 * @return
	 * @author 01369521
	 * @date 2017年12月13日
	 */
	private static Config buildConfig(ClaimTopologyProperties claimTopologyProperties) {
		Config cfg = new Config();
		cfg.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, claimTopologyProperties.getMaxSpoutPending());// 一次读取KAFKA会读取多少数据
		cfg.put(Config.TOPOLOGY_WORKER_CHILDOPTS, claimTopologyProperties.getJavaOpts());// 自定义JAVA配置
		cfg.put("spout.single.thread", true);
		cfg.setNumWorkers(claimTopologyProperties.getWorkerNum());// jvm数量，本地模式无效
		cfg.setDebug(claimTopologyProperties.isDebugFlag());// 本地模式调试时开启
		cfg.setMessageTimeoutSecs(60);//设置超时时间
		return cfg;
	}

}
