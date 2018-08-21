package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfg;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfgExample;

/**
 * HbaseQueryCfgManager类
 *
 * @author 01369626
*/
public interface HbaseQueryCfgManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(HbaseQueryCfgExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(HbaseQueryCfgExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param id 主键
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(String id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(HbaseQueryCfg record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(HbaseQueryCfg record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<HbaseQueryCfg> 记录列表
	 *
    */
    List<HbaseQueryCfg> selectByExample(HbaseQueryCfgExample example);

	/**
	 * 按主键查询
	 * 
	 * @param id 主键
	 * 
	 * @return HbaseQueryCfg 记录 
	 *
    */
    HbaseQueryCfg selectByPrimaryKey(String id);

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(HbaseQueryCfg record, HbaseQueryCfgExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(HbaseQueryCfg record,  HbaseQueryCfgExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(HbaseQueryCfg record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(HbaseQueryCfg record);
	
}