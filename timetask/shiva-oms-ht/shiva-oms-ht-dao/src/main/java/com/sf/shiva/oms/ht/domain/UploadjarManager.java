package com.sf.shiva.oms.ht.domain;

import java.util.Date;

/**
 * 该类与数据库中的表tm_uploadjar_manager对应 
 * 
 * @author 01369626
 */
public class UploadjarManager {
    private String id;

    private String jarName;

    private String name;

    private String createEmp;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}