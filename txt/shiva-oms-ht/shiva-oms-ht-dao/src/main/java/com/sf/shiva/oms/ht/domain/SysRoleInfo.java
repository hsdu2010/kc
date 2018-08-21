package com.sf.shiva.oms.ht.domain;

/**
 * 该类与数据库中的表sys_role_info对应 
 * 
 * @author 01139932
 */
public class SysRoleInfo {
    private Long id;

    private String sysRoleUid;

    private String sysRoleName;

    private String sysRoleDesc;

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

    public String getSysRoleUid() {
        return sysRoleUid;
    }

    public void setSysRoleUid(String sysRoleUid) {
        this.sysRoleUid = sysRoleUid == null ? null : sysRoleUid.trim();
    }

    public String getSysRoleName() {
        return sysRoleName;
    }

    public void setSysRoleName(String sysRoleName) {
        this.sysRoleName = sysRoleName == null ? null : sysRoleName.trim();
    }

    public String getSysRoleDesc() {
        return sysRoleDesc;
    }

    public void setSysRoleDesc(String sysRoleDesc) {
        this.sysRoleDesc = sysRoleDesc == null ? null : sysRoleDesc.trim();
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