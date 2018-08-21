package com.sf.shiva.oms.ht.service.system;

import java.util.List;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.SysRoleInfo;
import com.sf.shiva.oms.ht.domain.SysRoleInfoExample;
import com.sf.shiva.oms.ht.domain.SysUserRoleInfo;

/**
 * RoleService类
 *
 * @author 01139932
*/
public interface SysRoleInfoService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(SysRoleInfoExample example);

	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer deleteByPrimaryKey(Long id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer insert(SysRoleInfo record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<Role> 记录列表 
	 *
    */
    Page<SysRoleInfo> selectByExample(SysRoleInfoExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return Role 记录 
	 *
    */
	SysRoleInfo selectByPrimaryKey(Long id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(SysRoleInfo record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(SysRoleInfo record);
    
	List<SysUserRoleInfo> getUserRoleList(String userNo);
	
	 int changeUserRole(String roleId, Boolean state, String userNo);
	 
	 Integer updateToDeleteStatus(Long id);
	
}