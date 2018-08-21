package com.sf.shiva.oms.ht.manager;

import java.util.List;

import com.sf.shiva.oms.ht.domain.SysRoleInfo;
import com.sf.shiva.oms.ht.domain.SysRoleInfoExample;
import com.sf.shiva.oms.ht.domain.SysUserRoleInfo;

/**
 * RoleManager类
 *
 * @author 01139932
*/
public interface SysRoleInfoManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(SysRoleInfoExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(SysRoleInfoExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(Long id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(SysRoleInfo record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(SysRoleInfo record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<Role> 记录列表
	 *
    */
    List<SysRoleInfo> selectByExample(SysRoleInfoExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(SysRoleInfo record, SysRoleInfoExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(SysRoleInfo record,  SysRoleInfoExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(SysRoleInfo record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(SysRoleInfo record);
	
    int updateToDeleteStatus(Long id);
    
    List<SysUserRoleInfo> getUserRoleList(String userNo);
    
    
/*    Role getRoleById(String roleId);
    
    List<Role> getAllRole();
    int addRole(Role role);
    
    int updateRole(Role role);*/
    

    int changeUserRole(String roleId, Boolean state, String userNo);
}