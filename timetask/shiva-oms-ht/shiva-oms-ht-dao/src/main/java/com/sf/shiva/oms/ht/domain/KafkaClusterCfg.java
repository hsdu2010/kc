package com.sf.shiva.oms.ht.domain;

import java.util.Date;

/**
 * 该类与数据库中的表tm_kafka_cluster_cfg对应 
 * 
 * @author 01369626
 */
public class KafkaClusterCfg {
    private String id;

    private String clusterName;

    private String monitorUrl;

    private Date currenttime;

    private String operator;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getMonitorUrl() {
        return monitorUrl;
    }

    public void setMonitorUrl(String monitorUrl) {
        this.monitorUrl = monitorUrl;
    }

    public Date getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(Date currenttime) {
        this.currenttime = currenttime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}