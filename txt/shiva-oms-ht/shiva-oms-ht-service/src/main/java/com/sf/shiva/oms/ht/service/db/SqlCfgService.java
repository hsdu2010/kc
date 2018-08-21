package com.sf.shiva.oms.ht.service.db;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.domain.SqlCfgExample;

/**
 * SqlCfgService类
 *
 * @author 01369626
*/
public interface SqlCfgService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(SqlCfgExample example);

	/**
	 * 按主键删除
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer deleteByPrimaryKey(String sqlId);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer insert(SqlCfg record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<SqlCfg> 记录列表 
	 *
    */
    Page<SqlCfg> selectByExample(SqlCfgExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return SqlCfg 记录 
	 *
    */
	SqlCfg selectByPrimaryKey(String sqlId);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(SqlCfg record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(SqlCfg record);
    
    /**
     * 根据查询名获取记录
     * @param sqlName
     * @return
     * @author 01369626
     * @date 2017年12月1日
     */
    Page<SqlCfg> searchBySqlName(String sqlName, Integer pageNum, Integer pageSize);
	
}