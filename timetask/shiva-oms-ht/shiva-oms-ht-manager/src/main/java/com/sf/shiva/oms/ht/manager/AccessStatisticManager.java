package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.AccessStatisticKey;
import com.sf.shiva.oms.ht.domain.AccessStatistic;
import com.sf.shiva.oms.ht.domain.AccessStatisticExample;

/**
 * AccessStatisticManager类
 *
 * @author 01369626
*/
public interface AccessStatisticManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(AccessStatisticExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(AccessStatisticExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param key 主键
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(AccessStatisticKey key);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(AccessStatistic record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(AccessStatistic record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<AccessStatistic> 记录列表
	 *
    */
    List<AccessStatistic> selectByExample(AccessStatisticExample example);

	/**
	 * 按主键查询
	 * 
	 * @param key 主键
	 * 
	 * @return AccessStatistic 记录 
	 *
    */
    AccessStatistic selectByPrimaryKey(AccessStatisticKey key);

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(AccessStatistic record, AccessStatisticExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(AccessStatistic record,  AccessStatisticExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(AccessStatistic record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(AccessStatistic record);
	
}