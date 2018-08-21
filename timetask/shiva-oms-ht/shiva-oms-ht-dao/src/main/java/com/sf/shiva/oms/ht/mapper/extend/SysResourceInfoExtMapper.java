package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sf.shiva.oms.ht.domain.SysResourceInfo;
import com.sf.shiva.oms.ht.domain.SysRoleResourceInfo;

public interface SysResourceInfoExtMapper {
	/*
	 * int getSubResourceCount(String parentResUrl);
	 * 
	 * List<SysResourceInfo> getSubResource(String parentResUrl);
	 * 
	 * List<SysResourceInfo> selectResourceByUserNo(String username);
	 */

	int updateToDeleteStatus(Long id);

	int updateByPrimaryKeySelective(SysResourceInfo recore);

	SysResourceInfo selectByPrimaryKey(Long id);

	List<SysResourceInfo> getResourceList();

	List<SysRoleResourceInfo> getResourceByRoleId(String roleId);

	int deleteByRoleId(String roleId);

	int insertRoleResourceList(@Param("roleId") String roleId, @Param("resList") List<String> resourceIdList);

	List<SysResourceInfo> selectSideMenu(@Param("id") Long id);

	List<SysResourceInfo> selectChildMenu(@Param("id") Long id);
	/*
	 * List<SysResourceInfo> selectSideMenu(@Param("userNo")String
	 * userNo, @Param("parentResUrl")String parentResUrl);
	 * 
	 * List<SysResourceInfo> selectChildMenu(@Param("userNo")String
	 * userNo, @Param("parentResUrl")String parentResUrl);
	 * 
	 * int getParentById(@Param("id")Integer id);
	 */
}
