package com.sf.shiva.oms.ht.domain;

import java.util.Date;

/**
 * 该类与数据库中的表tm_redis_cfg对应 
 * 
 * @author 01369626
 */
public class RedisCfg {
    private String id;

    private String moduleName;

    private String address;

    private String masterName;

    private Integer timeout;

    private Byte useSentinel;

    private String password;

    private String queryKeys;

    private Date createTm;

    private String createEmp;

    private Date modifyTm;

    private String modifyEmp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Byte getUseSentinel() {
        return useSentinel;
    }

    public void setUseSentinel(Byte useSentinel) {
        this.useSentinel = useSentinel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQueryKeys() {
        return queryKeys;
    }

    public void setQueryKeys(String queryKeys) {
        this.queryKeys = queryKeys;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

    public String getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(String modifyEmp) {
        this.modifyEmp = modifyEmp;
    }
}