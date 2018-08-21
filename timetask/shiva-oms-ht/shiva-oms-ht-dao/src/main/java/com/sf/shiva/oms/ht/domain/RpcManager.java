package com.sf.shiva.oms.ht.domain;

import java.util.Date;

/**
 * 该类与数据库中的表tm_rpc_manager对应 
 * 
 * @author 01369626
 */
public class RpcManager {
    private String id;

    private String createEmp;

    private String jarName;

    private String applicationName;

    private String packagePath;

    private Date createtime;

    private Date updatetime;

    private String modifyEmp;

    private String zkUrl;

    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(String modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public String getZkUrl() {
        return zkUrl;
    }

    public void setZkUrl(String zkUrl) {
        this.zkUrl = zkUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}