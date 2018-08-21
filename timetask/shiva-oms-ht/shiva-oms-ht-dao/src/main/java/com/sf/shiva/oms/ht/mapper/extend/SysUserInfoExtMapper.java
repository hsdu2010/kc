package com.sf.shiva.oms.ht.mapper.extend;

import com.sf.shiva.oms.ht.domain.SysUserInfo;

/**
 * 
 * @author 01139932
 */
public interface SysUserInfoExtMapper {

	int updateToDeleteStatus(Long id);

	int updateByPrimaryKeySelective(SysUserInfo recore);

	SysUserInfo selectByPrimaryKey(Long id);
	
	SysUserInfo selectByUserName(String userName);
}