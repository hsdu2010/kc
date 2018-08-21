package com.sf.shiva.oms.ht.service.system;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.SysUserInfo;
import com.sf.shiva.oms.ht.domain.SysUserInfoExample;

/**
 * UserService类
 *
 * @author 01139932
*/
public interface SysUserInfoService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(SysUserInfoExample example);

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
    Integer insert(SysUserInfo record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<User> 记录列表 
	 *
    */
    Page<SysUserInfo> selectByExample(SysUserInfoExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return User 记录 
	 *
    */
	SysUserInfo selectByPrimaryKey(Long id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(SysUserInfo record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(SysUserInfo record);
    
    Integer updateToDeleteStatus(Long id);
    
    SysUserInfo selectUserByUserName(String userName);
    
    //String getCurrentUserNo();
	
}