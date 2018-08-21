package com.sf.shiva.oms.ht.service;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.RedisCfg;
import com.sf.shiva.oms.ht.domain.RedisCfgExample;

/**
 * RedisCfgService类
 *
 * @author 01369626
*/
public interface RedisCfgService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(RedisCfgExample example);

	/**
	 * 按主键删除
	 * 
	 * @param id id
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer deleteByPrimaryKey(String id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer insert(RedisCfg record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<RedisCfg> 记录列表 
	 *
    */
    Page<RedisCfg> selectByExample(RedisCfgExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id id
	 * 
	 * @return RedisCfg 记录 
	 *
    */
	RedisCfg selectByPrimaryKey(String id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(RedisCfg record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(RedisCfg record);
    
    public Page<RedisCfg> searchByModuleName(String moduleName, Integer pageNum, Integer pageSize);
	
}