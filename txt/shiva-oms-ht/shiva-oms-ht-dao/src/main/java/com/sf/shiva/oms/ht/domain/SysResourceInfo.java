package com.sf.shiva.oms.ht.domain;

/**
 * 该类与数据库中的表sys_resource_info对应 
 * 
 * @author 01369626
 */
public class SysResourceInfo {
    private Long id;

    private String sysResourceUid;

    private String sysResourceName;

    private Integer sysResourceLevel;

    private String sysResourcePath;

    private String sysParentUid;

    private String createtime;

    private String updatetime;

    private String updateuid;

    private Boolean delStatus;

    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysResourceUid() {
        return sysResourceUid;
    }

    public void setSysResourceUid(String sysResourceUid) {
        this.sysResourceUid = sysResourceUid;
    }

    public String getSysResourceName() {
        return sysResourceName;
    }

    public void setSysResourceName(String sysResourceName) {
        this.sysResourceName = sysResourceName;
    }

    public Integer getSysResourceLevel() {
        return sysResourceLevel;
    }

    public void setSysResourceLevel(Integer sysResourceLevel) {
        this.sysResourceLevel = sysResourceLevel;
    }

    public String getSysResourcePath() {
        return sysResourcePath;
    }

    public void setSysResourcePath(String sysResourcePath) {
        this.sysResourcePath = sysResourcePath;
    }

    public String getSysParentUid() {
        return sysParentUid;
    }

    public void setSysParentUid(String sysParentUid) {
        this.sysParentUid = sysParentUid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuid() {
        return updateuid;
    }

    public void setUpdateuid(String updateuid) {
        this.updateuid = updateuid;
    }

    public Boolean getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Boolean delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}