package com.sf.shiva.oms.ht.domain;

/**
 * 该类与数据库中的表sys_area_info对应 
 * 
 * @author 01139932
 */
public class SysAreaInfo {
    private Long id;

    private String sysAreaUid;

    private String sysAreaName;

    private Integer sysAreaLevel;

    private String sysParentUid;

    private String createtime;

    private String updatetime;

    private String updateuid;

    private Boolean delStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysAreaUid() {
        return sysAreaUid;
    }

    public void setSysAreaUid(String sysAreaUid) {
        this.sysAreaUid = sysAreaUid == null ? null : sysAreaUid.trim();
    }

    public String getSysAreaName() {
        return sysAreaName;
    }

    public void setSysAreaName(String sysAreaName) {
        this.sysAreaName = sysAreaName == null ? null : sysAreaName.trim();
    }

    public Integer getSysAreaLevel() {
        return sysAreaLevel;
    }

    public void setSysAreaLevel(Integer sysAreaLevel) {
        this.sysAreaLevel = sysAreaLevel;
    }

    public String getSysParentUid() {
        return sysParentUid;
    }

    public void setSysParentUid(String sysParentUid) {
        this.sysParentUid = sysParentUid == null ? null : sysParentUid.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getUpdateuid() {
        return updateuid;
    }

    public void setUpdateuid(String updateuid) {
        this.updateuid = updateuid == null ? null : updateuid.trim();
    }

    public Boolean getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Boolean delStatus) {
        this.delStatus = delStatus;
    }
}