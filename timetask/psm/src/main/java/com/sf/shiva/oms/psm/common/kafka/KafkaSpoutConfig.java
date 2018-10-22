package com.sf.shiva.oms.psm.common.kafka;

import java.io.Serializable;

/**
 * 描述：kafkaSpout配置信息
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
public class KafkaSpoutConfig implements Serializable {

    private static final long serialVersionUID = -5909858007414574204L;

    /** kafka集群url */
    private String monitorUrl;
    /** 集群名称 */
    private String clusterName;
    /** 消费组 */
    private String systemId;
    /** 校验码 */
    private String token;
    /** 主题 */
    private String topic;
    /** kafkaSpout 自定义Name */
    private String spoutId;

    public String getMonitorUrl() {
        return monitorUrl;
    }

    public void setMonitorUrl(String monitorUrl) {
        this.monitorUrl = monitorUrl;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSpoutId() {
        return spoutId;
    }

    public void setSpoutId(String spoutId) {
        this.spoutId = spoutId;
    }

}
