package com.sf.shiva.oms.psm.topology;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.bolt.PackageStatusSendBolt;
import com.sf.shiva.oms.psm.cfgmanage.PackageStatusSendTopologyProperties;
import com.sf.shiva.oms.psm.cfgmanage.SystemProperties;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.enumtype.StreamIdEnum;
import com.sf.shiva.oms.psm.common.enumtype.TopologyNameEnum;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.spout.PackageStatusSendLoadSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

/**
 * 描述：包裹状态定时应用
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月12日      80002517         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002517
 * @since 1.0
 */
public class PackageStatusSendLoadTopology {

    private static final Logger logger = LoggerFactory.getLogger(PackageStatusSendLoadTopology.class);

    private PackageStatusSendLoadTopology() {
        super();
    }

    public static void main(String[] args) throws InvalidTopologyException {
        try {
            SystemProperties systemCfg = SpringContext.getInstance().getBean(SystemProperties.class);// 获取System配置信息
            PackageStatusSendTopologyProperties topologyCfg = SpringContext.getInstance().getBean(PackageStatusSendTopologyProperties.class);// 获取Topology配置信息
            if (StringUtils.equals(PackageStatusConstant.SYSTEM_ENV_DEV, systemCfg.getSystemEnv())) {
                // "packageStatusSendLoadTopology"
                // 是topology的id，可在jstorm命令中通过该id来管理topology
                new LocalCluster().submitTopology(TopologyNameEnum.PACKAGE_STATUS_SEND_LOAD.getName(), buildConfig(topologyCfg), buildTopologyBuilder(topologyCfg).createTopology());
            } else {
                StormSubmitter.submitTopology(TopologyNameEnum.PACKAGE_STATUS_SEND_LOAD.getName(), buildConfig(topologyCfg), buildTopologyBuilder(topologyCfg).createTopology());
            }
        } catch (AlreadyAliveException e) {
            logger.error("PackageStatusSendLoadTopology error", e);
        }
    }

    /**
     * 构建buildTopologyBuilderStream
     * 
     * @param topologyCfg
     *            topologyDisConf topology配置信息
     * @return TopologyBuilder对象
     * @author 80002517
     * @date 2017年12月15日
     */
    private static TopologyBuilder buildTopologyBuilder(PackageStatusSendTopologyProperties topologyCfg) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("packageStatusSendLoadSpout", new PackageStatusSendLoadSpout(), topologyCfg.getSpoutThreadNum());
        builder.setBolt("pcakageStatusSendBolt", new PackageStatusSendBolt(), topologyCfg.getPackageStatusSendBoltThreadNum()).localOrShuffleGrouping("packageStatusSendLoadSpout", StreamIdEnum.PACKAGE_STATUS_RESEND.getStreamId());
        return builder;
    }

    /**
     * 构建配置对象
     * 
     * @param topologyCfg
     *            配置信息
     * @return 配置对象
     * @author 80002517
     * @date 2017年12月15日
     */
    private static Config buildConfig(PackageStatusSendTopologyProperties topologyCfg) {
        Config cfg = new Config();
        cfg.put(Config.TOPOLOGY_WORKER_CHILDOPTS, topologyCfg.getJavaOpts());// 自定义JAVA配置
        cfg.setNumWorkers(topologyCfg.getWorkerNum());// jvm数量，本地模式无效
        cfg.setDebug(topologyCfg.isDebugFlag());// 本地模式调试时开启
        cfg.setMessageTimeoutSecs(60);
        return cfg;
    }
}
