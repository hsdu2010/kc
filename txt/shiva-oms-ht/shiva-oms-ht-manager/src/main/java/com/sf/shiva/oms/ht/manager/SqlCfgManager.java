package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.domain.SqlCfgExample;

/**
 * SqlCfgManager类
 *
 * @author 01369626
*/
public interface SqlCfgManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(SqlCfgExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(SqlCfgExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(String sqlId);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(SqlCfg record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(SqlCfg record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<SqlCfg> 记录列表
	 *
    */
    List<SqlCfg> selectByExample(SqlCfgExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(SqlCfg record, SqlCfgExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(SqlCfg record,  SqlCfgExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(SqlCfg record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(SqlCfg record);
	
}