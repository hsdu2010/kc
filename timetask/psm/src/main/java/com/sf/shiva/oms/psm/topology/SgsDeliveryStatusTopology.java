package com.sf.shiva.oms.psm.topology;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.bolt.DeliveryStatusCalBolt;
import com.sf.shiva.oms.psm.bolt.PackageStatusSendBolt;
import com.sf.shiva.oms.psm.cfgmanage.SgsTopologyProperties;
import com.sf.shiva.oms.psm.cfgmanage.SystemProperties;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.enumtype.StreamIdEnum;
import com.sf.shiva.oms.psm.common.enumtype.TopologyNameEnum;
import com.sf.shiva.oms.psm.common.kafka.KafkaSpoutConfig;
import com.sf.shiva.oms.psm.common.kafka.KafkaSpoutFactory;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.spout.DeliveryStatusScheme;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

/**
 * 描述：SGS派件状态Topology
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月7日      01369610         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369610
 * @since 1.0
 */
public class SgsDeliveryStatusTopology {
	
	private SgsDeliveryStatusTopology() {
		super();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(SgsDeliveryStatusTopology.class);
	
	public static void main(String[] args) throws InvalidTopologyException {
		try{
			SystemProperties systemCfg = SpringContext.getInstance().getBean(SystemProperties.class);// 获取System配置信息
			SgsTopologyProperties topologyCfg = SpringContext.getInstance().getBean(SgsTopologyProperties.class);// 获取SGS Topology配置信息
			if (StringUtils.equals(PackageStatusConstant.SYSTEM_ENV_DEV, systemCfg.getSystemEnv())) {
				new LocalCluster().submitTopology(TopologyNameEnum.SGS_DELIVER.getName(), buildConfig(topologyCfg), buildTopologyBuilder(topologyCfg).createTopology());
			} else {
				StormSubmitter.submitTopology(TopologyNameEnum.SGS_DELIVER.getName(), buildConfig(topologyCfg), buildTopologyBuilder(topologyCfg).createTopology());
			}
		} catch (AlreadyAliveException e) {
			logger.error("SgsDeliveryStatusTopology error", e);
		}
	}

	/** 构建buildTopologyBuilder
	 * @param SgsTopologyProperties topology配置信息
	 * @return TopologyBuilder对象
	 * @author 01369610
	 * @date 2017年12月13日
	 */
	private static TopologyBuilder buildTopologyBuilder(SgsTopologyProperties topologyCfg) {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("deliveryStatusSpout", KafkaSpoutFactory.create(SpringContext.getInstance().getBean("deliveryStatusSpoutConfig", KafkaSpoutConfig.class),
						new DeliveryStatusScheme()), topologyCfg.getSpoutThreadNum());
		builder.setBolt("deliveryStatusCalBolt", new DeliveryStatusCalBolt(), topologyCfg.getBoltThreadNum()).localOrShuffleGrouping("deliveryStatusSpout");
		builder.setBolt("packageStatusSendBolt", new PackageStatusSendBolt(), topologyCfg.getPackageStatusSendBoltThreadNum()).localOrShuffleGrouping("deliveryStatusCalBolt",StreamIdEnum.PACKAGE_STATUS_SEND.getStreamId());
		return builder;
	}

	/** 构建配置对象
	 * @param SgsTopologyProperties 配置信息
	 * @return 配置对象
	 * @author 01369610
	 * @date 2017年12月13日
	 */
	private static Config buildConfig(SgsTopologyProperties topologyCfg) {
		Config cfg = new Config();
		cfg.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, topologyCfg.getMaxSpoutPending());// 一次读取KAFKA会读取多少数据
		cfg.put(Config.TOPOLOGY_WORKER_CHILDOPTS, topologyCfg.getJavaOpts());// 自定义JAVA配置
		cfg.setNumWorkers(topologyCfg.getWorkerNum());// jvm数量，本地模式无效
		cfg.setDebug(topologyCfg.isDebugFlag());// 本地模式调试时开启
		cfg.put("spout.single.thread", true);
		cfg.setMessageTimeoutSecs(60);
		return cfg;
	}
}
