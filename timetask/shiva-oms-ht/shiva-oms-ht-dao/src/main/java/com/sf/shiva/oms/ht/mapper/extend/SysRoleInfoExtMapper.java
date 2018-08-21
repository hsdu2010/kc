package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sf.shiva.oms.ht.domain.SysRoleInfo;
import com.sf.shiva.oms.ht.domain.SysUserRoleInfo;

public interface SysRoleInfoExtMapper {

	int updateToDeleteStatus(Long id);

	int updateByPrimaryKeySelective(SysRoleInfo recore);

	SysRoleInfo selectByPrimaryKey(Long id);

	/** 通过工号查找用户角色的绑定状态 */
	List<SysUserRoleInfo> getUserRoleByUserNo(String userNo);

	int bindUserRole(@Param("createtime") String createtime, @Param("updatetime") String updatetime,
			@Param("roleId") String roleId, @Param("userNo") String userNo);

	int unBindUserRole(@Param("roleId") String roleId, @Param("userNo") String userNo);
}
