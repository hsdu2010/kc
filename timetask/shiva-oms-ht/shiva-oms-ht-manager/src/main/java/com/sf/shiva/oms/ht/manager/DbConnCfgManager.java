package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.DbConnCfgExample;

/**
 * DbConnCfgManager类
 *
 * @author 01369626
*/
public interface DbConnCfgManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(DbConnCfgExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(DbConnCfgExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param dbId id
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(String dbId);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(DbConnCfg record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(DbConnCfg record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<DbConnCfg> 记录列表
	 *
    */
    List<DbConnCfg> selectByExample(DbConnCfgExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(DbConnCfg record, DbConnCfgExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(DbConnCfg record,  DbConnCfgExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(DbConnCfg record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(DbConnCfg record);
	
}