package com.sf.shiva.oms.ht.domain;

/**
 * 该类与数据库中的表sys_user_info对应 
 * 
 * @author 01139932
 */
public class SysUserInfo {
    private Long id;

    private String sysUserUid;

    private String sysUsername;

    private String sysPwd;

    private String sysName;

    private String sysBirth;

    private String sysSex;

    private String sysCertType;

    private String sysCertVal;

    private String sysUserProv;

    private String sysProvName;

    private String sysUserCity;

    private String sysCityName;

    private String sysUserImg;

    private Integer sysImgCount;

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

    public String getSysUserUid() {
        return sysUserUid;
    }

    public void setSysUserUid(String sysUserUid) {
        this.sysUserUid = sysUserUid == null ? null : sysUserUid.trim();
    }

    public String getSysUsername() {
        return sysUsername;
    }

    public void setSysUsername(String sysUsername) {
        this.sysUsername = sysUsername == null ? null : sysUsername.trim();
    }

    public String getSysPwd() {
        return sysPwd;
    }

    public void setSysPwd(String sysPwd) {
        this.sysPwd = sysPwd == null ? null : sysPwd.trim();
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    public String getSysBirth() {
        return sysBirth;
    }

    public void setSysBirth(String sysBirth) {
        this.sysBirth = sysBirth == null ? null : sysBirth.trim();
    }

    public String getSysSex() {
        return sysSex;
    }

    public void setSysSex(String sysSex) {
        this.sysSex = sysSex == null ? null : sysSex.trim();
    }

    public String getSysCertType() {
        return sysCertType;
    }

    public void setSysCertType(String sysCertType) {
        this.sysCertType = sysCertType == null ? null : sysCertType.trim();
    }

    public String getSysCertVal() {
        return sysCertVal;
    }

    public void setSysCertVal(String sysCertVal) {
        this.sysCertVal = sysCertVal == null ? null : sysCertVal.trim();
    }

    public String getSysUserProv() {
        return sysUserProv;
    }

    public void setSysUserProv(String sysUserProv) {
        this.sysUserProv = sysUserProv == null ? null : sysUserProv.trim();
    }

    public String getSysProvName() {
        return sysProvName;
    }

    public void setSysProvName(String sysProvName) {
        this.sysProvName = sysProvName == null ? null : sysProvName.trim();
    }

    public String getSysUserCity() {
        return sysUserCity;
    }

    public void setSysUserCity(String sysUserCity) {
        this.sysUserCity = sysUserCity == null ? null : sysUserCity.trim();
    }

    public String getSysCityName() {
        return sysCityName;
    }

    public void setSysCityName(String sysCityName) {
        this.sysCityName = sysCityName == null ? null : sysCityName.trim();
    }

    public String getSysUserImg() {
        return sysUserImg;
    }

    public void setSysUserImg(String sysUserImg) {
        this.sysUserImg = sysUserImg == null ? null : sysUserImg.trim();
    }

    public Integer getSysImgCount() {
        return sysImgCount;
    }

    public void setSysImgCount(Integer sysImgCount) {
        this.sysImgCount = sysImgCount;
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