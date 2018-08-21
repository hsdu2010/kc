package com.sf.shiva.oms.ht.domain;

public class SysUserRoleInfo extends SysRoleInfo {

    /**
     * 角色-用户是否已绑定，
     */
    private Boolean bindState;

    public SysUserRoleInfo() {}
    
    public Boolean getBindState() {
        return bindState;
    }
    public void setBindState(Boolean bindState) {
        this.bindState = bindState;
    }

}
