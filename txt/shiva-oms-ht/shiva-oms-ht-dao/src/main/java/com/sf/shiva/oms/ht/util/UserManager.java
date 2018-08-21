package com.sf.shiva.oms.ht.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.sf.shiva.oms.ht.domain.SysUserInfo;


public class UserManager {

	public static String getCurrentUserNo(){
		Subject currentUser = SecurityUtils.getSubject();
		Object obj = currentUser.getPrincipal();
		if (obj == null) {
			return "";
		}
		String className = obj.getClass().getSimpleName();
		if ("String".equals(className)) {// cas认证
			return (String) obj;
		} else if ("SysUserInfo".equals(className)) {//自定义认证
			SysUserInfo userInfo = (SysUserInfo) obj;
			return userInfo.getSysUsername();
		} else
			return "";
	} 
}
