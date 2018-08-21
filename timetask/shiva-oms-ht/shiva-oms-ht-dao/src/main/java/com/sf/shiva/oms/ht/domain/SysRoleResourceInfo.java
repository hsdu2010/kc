package com.sf.shiva.oms.ht.domain;


public class SysRoleResourceInfo extends SysResourceInfo {
    /**
     * 角色是否已绑定这个资源
     */
    private Boolean bindState;

    public SysRoleResourceInfo() {}

    public Boolean getBindState() {
        return bindState;
    }
    public void setBindState(Boolean bindState) {
        this.bindState = bindState;
    }
}
