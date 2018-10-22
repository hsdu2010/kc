package com.sf.shiva.oms.psm.common.kafka;

import java.io.Serializable;
import java.util.Arrays;


import com.sf.kafka.check.util.AuthUtil;

import backtype.storm.spout.Scheme;
import backtype.storm.spout.SchemeAsMultiScheme;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

/**
 * 
 * 描述：kafkaSpout工厂类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年9月11日      80002137         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002137
 * @since 1.0
 */
public class KafkaSpoutFactory implements Serializable {

    private static final long serialVersionUID = 7580676913647428393L;

    /** 公司kafka在zk上的根目录 */
    public static final String KAFKA_ZK_ROOT = "/transactional/fvp";
    
    /** kafka zkhost 默认端口号*/
    private static final int DEFAULT_ZK_PORT = 2181;

    /**
     * 根据配置信息创建 kafkaSpout
     * 
     * @param conf
     *            kafka配置信息
     * @param scheme
     *            数据源字节数组转换规则对象
     * @return kafkaSpout 数据源接收组件
     * @author 80002137
     * @date 2017年9月11日
     */
    public static KafkaSpout create(KafkaSpoutConfig conf, Scheme scheme) {
        // 获取zookeeper信息
        String zkServers = AuthUtil.getZkServers(conf.getClusterName(), conf.getToken(), conf.getSystemId(), conf.getMonitorUrl());
        String zkId = new StringBuilder(conf.getSystemId()).append("~").append(conf.getClusterName()).append("~").append(conf.getTopic()).toString();
        BrokerHosts brokerHosts = new ZkHosts(zkServers);// 用以获取Kafka的broker和partition的信息
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, conf.getTopic(), KAFKA_ZK_ROOT, zkId);
        setSpoutZkConfig(zkServers, spoutConfig);
        spoutConfig.fetchMaxWait = 5000;//读取kafka最长等待时间，设置5000避免出现socket超时
        spoutConfig.scheme = new SchemeAsMultiScheme(scheme);// 设置kafka数据由字节数组转换成scheme规定的对象
        spoutConfig.maxOffsetBehind = Integer.MAX_VALUE;
        return new KafkaSpout(spoutConfig);
    }
    
    /**
     * 设置zookeeper的ip地址和端口号
     * @param zkServers zk地址字符串
     * @param spoutConfig spout配置
     * @author 01369626
     * @date 2018年1月22日
     */
    private static void setSpoutZkConfig(String zkServers, SpoutConfig spoutConfig) {
        String[] brokensStr;
        int zkPort = DEFAULT_ZK_PORT;
        if(zkServers.indexOf(":") > -1) {//返回zk地址带有端口号
            brokensStr = zkServers.substring(0, zkServers.indexOf("/")).replace(":" + zkServers.split(",")[0].split(":")[1], "").split(",");
            zkPort = Integer.parseInt(zkServers.split(",")[0].split(":")[1]);// 获取zookeeper的端口号
        }else {//返回zk地址没有端口号
            brokensStr = zkServers.substring(0, zkServers.indexOf("/")).split(",");
        }
        spoutConfig.zkServers = Arrays.asList(brokensStr);
        spoutConfig.zkPort = zkPort;
    }
    
}
