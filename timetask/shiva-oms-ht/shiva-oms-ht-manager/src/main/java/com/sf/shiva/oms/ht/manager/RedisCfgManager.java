package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.RedisCfg;
import com.sf.shiva.oms.ht.domain.RedisCfgExample;

/**
 * RedisCfgManager类
 *
 * @author 01369626
*/
public interface RedisCfgManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(RedisCfgExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(RedisCfgExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param id id
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
    int insert(RedisCfg record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(RedisCfg record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<RedisCfg> 记录列表
	 *
    */
    List<RedisCfg> selectByExample(RedisCfgExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(RedisCfg record, RedisCfgExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(RedisCfg record,  RedisCfgExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(RedisCfg record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(RedisCfg record);
	
}