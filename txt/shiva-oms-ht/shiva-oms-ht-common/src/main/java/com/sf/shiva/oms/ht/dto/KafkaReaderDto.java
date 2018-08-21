package com.sf.shiva.oms.ht.dto;

public class KafkaReaderDto {
    private String clusterName;
    private String monitorUrl;
    private String topicName;
    private int maxSize;
    private String filterStr;
    private String token;
    private boolean deserialize;
    
    public String getTopicName() {
        return topicName;
    }
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    public String getFilterStr() {
        return filterStr;
    }
    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
    }
    public boolean isDeserialize() {
        return deserialize;
    }
    public void setDeserialize(boolean deserialize) {
        this.deserialize = deserialize;
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
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    
}
