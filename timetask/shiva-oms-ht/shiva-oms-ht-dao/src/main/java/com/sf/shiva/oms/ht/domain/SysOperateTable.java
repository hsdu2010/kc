package com.sf.shiva.oms.ht.domain;

/**
 * 该类与数据库中的表sys_oprt_table对应 
 * 
 * @author 01139932
 */
public class SysOperateTable {
    private Long id;

    private String sysTableUid;

    private String sysTableEname;

    private String sysTableCname;

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

    public String getSysTableUid() {
        return sysTableUid;
    }

    public void setSysTableUid(String sysTableUid) {
        this.sysTableUid = sysTableUid == null ? null : sysTableUid.trim();
    }

    public String getSysTableEname() {
        return sysTableEname;
    }

    public void setSysTableEname(String sysTableEname) {
        this.sysTableEname = sysTableEname == null ? null : sysTableEname.trim();
    }

    public String getSysTableCname() {
        return sysTableCname;
    }

    public void setSysTableCname(String sysTableCname) {
        this.sysTableCname = sysTableCname == null ? null : sysTableCname.trim();
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