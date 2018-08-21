package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.SysUserInfo;
import com.sf.shiva.oms.ht.domain.SysUserInfoExample;

/**
 * UserManager类
 *
 * @author 01139932
*/
public interface SysUserInfoManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(SysUserInfoExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(SysUserInfoExample example);
	
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
    int insert(SysUserInfo record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(SysUserInfo record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<User> 记录列表
	 *
    */
    List<SysUserInfo> selectByExample(SysUserInfoExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(SysUserInfo record, SysUserInfoExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(SysUserInfo record,  SysUserInfoExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(SysUserInfo record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(SysUserInfo record);
    
    int updateToDeleteStatus(Long id);
    
    SysUserInfo selectUserByUserName(String userName);
	
}