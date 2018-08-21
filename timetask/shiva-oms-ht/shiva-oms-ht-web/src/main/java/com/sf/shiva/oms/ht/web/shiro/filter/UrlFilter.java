/*
* Copyright 2015-2020 SF-Express Tech Company. 
*/

package com.sf.shiva.oms.ht.web.shiro.filter;

import java.io.Serializable;

/**
 * 在dao层有一个相同的，使用dao层的
 * @date     2017年1月5日
 * @author   591791
 */
public class UrlFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String name; //url名称/描述
    
    private String url; //地址
    
    private String roles; //所需要的角色，可省略 ,多个角色用英文逗号分隔
    
    private String permissions; //所需要的权限，可省略 
    
    private Boolean available = false;  // 是否需要拦截
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getPermissions() {
        return permissions;
    }
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    
}
