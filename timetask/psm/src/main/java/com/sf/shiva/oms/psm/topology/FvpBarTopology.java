package com.sf.shiva.oms.psm.topology;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.bolt.FvpBarCalBolt;
import com.sf.shiva.oms.psm.bolt.PackageStatusSendBolt;
import com.sf.shiva.oms.psm.cfgmanage.FvpBarTopologyProperties;
import com.sf.shiva.oms.psm.cfgmanage.SystemProperties;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.enumtype.StreamIdEnum;
import com.sf.shiva.oms.psm.common.enumtype.TopologyNameEnum;
import com.sf.shiva.oms.psm.common.kafka.KafkaSpoutConfig;
import com.sf.shiva.oms.psm.common.kafka.KafkaSpoutFactory;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.spout.FvpBarScheme;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

/**
 * 
 * 描述：FVP巴枪计算Topology
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class FvpBarTopology {
	
	private FvpBarTopology() {
		super();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(FvpBarTopology.class);

    public static void main(String[] args) throws InvalidTopologyException {
    	try{
    		SystemProperties systemCfg = SpringContext.getInstance().getBean(SystemProperties.class);// 获取System配置信息
            FvpBarTopologyProperties topologyCfg = SpringContext.getInstance().getBean(FvpBarTopologyProperties.class);// 获取Topology配置信息
            if (StringUtils.equals(PackageStatusConstant.SYSTEM_ENV_DEV, systemCfg.getSystemEnv())) {
                // "checkCosTopology" 是topology的id，可在jstorm命令中通过该id来管理topology
                new LocalCluster().submitTopology(TopologyNameEnum.FVP_BAR.getName(), buildConfig(topologyCfg), buildTopologyBuilder(topologyCfg).createTopology());
            } else {
                StormSubmitter.submitTopology(TopologyNameEnum.FVP_BAR.getName(), buildConfig(topologyCfg), buildTopologyBuilder(topologyCfg).createTopology());
            }
		} catch (AlreadyAliveException e) {
			logger.error("FvpBarTopology error", e);
		}
    }

    /**
     * 构建TopologyBuilder
     * 
     * @param topologyCfg
     *            topology配置信息
     * @return TopologyBuilder对象
     * @author 568677
     * @date 2017年12月4日
     */
    private static TopologyBuilder buildTopologyBuilder(FvpBarTopologyProperties topologyCfg) {
        TopologyBuilder builder = new TopologyBuilder();
        // setSpout 方法的 第三个参数 3，就是Spout实例数，也是线程数，一个线程执行一个实例
        builder.setSpout("fvpBarSpout", KafkaSpoutFactory.create(SpringContext.getInstance().getBean("fvpBarSpoutConfig", KafkaSpoutConfig.class), new FvpBarScheme()), topologyCfg.getFvpBarSpoutThreadNum());
        // setBolt 第三个参数同上；setNumTasks方法原来是设置实例数量，不过在jstorm的后续版本中已不再使用，无效
        builder.setBolt("fvpBarCalBolt", new FvpBarCalBolt(), topologyCfg.getFvpBarCalBoltThreadNum()).localOrShuffleGrouping("fvpBarSpout");
        // shuffleGrouping 随机分组，把Tuple 随机分发到每个同名bolt实例中
        builder.setBolt("packageStatusSendBolt", new PackageStatusSendBolt(), topologyCfg.getPackageStatusSendBoltThreadNum()).localOrShuffleGrouping("fvpBarCalBolt", StreamIdEnum.PACKAGE_STATUS_SEND.getStreamId());
        return builder;
    }

    /**
     * 构建配置对象
     * 
     * @param topologyCfg
     *            topology配置信息
     * @return 配置对象
     * @author 568677
     * @date 2017年12月4日
     */
    private static Config buildConfig(FvpBarTopologyProperties topologyCfg) {
        Config cfg = new Config();
        cfg.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, topologyCfg.getMaxSpoutPending());// 一次读取KAFKA会读取多少数据
        cfg.put(Config.TOPOLOGY_WORKER_CHILDOPTS, topologyCfg.getJavaOpts());// 自定义JAVA配置
        cfg.put("spout.single.thread", true);//保证kafka读取线程安全
        cfg.setNumWorkers(topologyCfg.getFvpBarWorkerNum());// jvm数量，本地模式无效
        cfg.setDebug(topologyCfg.isDebugFlag());// 本地模式调试时开启
        cfg.setMessageTimeoutSecs(60);
        return cfg;
    }

}
