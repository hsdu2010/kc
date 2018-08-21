package com.sf.shiva.oms.ht.service.kafkamanager;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.KafkaClusterCfg;
import com.sf.shiva.oms.ht.domain.KafkaClusterCfgExample;

/**
 * KafkaClusterCfgService类
 *
 * @author 01369626
*/
public interface KafkaClusterCfgManagerService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(KafkaClusterCfgExample example);

	/**
	 * 按主键删除
	 * 
	 * @param id 
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
    Integer insert(KafkaClusterCfg record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<KafkaClusterCfg> 记录列表 
	 *
    */
    Page<KafkaClusterCfg> selectByExample(KafkaClusterCfgExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return KafkaClusterCfg 记录 
	 *
    */
	KafkaClusterCfg selectByPrimaryKey(String id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(KafkaClusterCfg record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(KafkaClusterCfg record);
	
}