package com.sf.shiva.oms.ht.service.db;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.DbConnCfgExample;

/**
 * DbConnCfgService类
 *
 * @author 01369626
*/
public interface DbConnCfgService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(DbConnCfgExample example);

	/**
	 * 按主键删除
	 * 
	 * @param dbId id
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer deleteByPrimaryKey(String dbId);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer insert(DbConnCfg record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<DbConnCfg> 记录列表 
	 *
    */
    Page<DbConnCfg> selectByExample(DbConnCfgExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param dbId id
	 * 
	 * @return DbConnCfg 记录 
	 *
    */
	DbConnCfg selectByPrimaryKey(String dbId);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(DbConnCfg record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(DbConnCfg record);
	
    /**
     * 根据连接名模糊查询
     * @param dbConnName
     * @param pageNum
     * @param pageSize
     * @return
     * @author 01369626
     * @date 2017年12月1日
     */
    Page<DbConnCfg> searchByConnName(String dbConnName, Integer pageNum, Integer pageSize);
}