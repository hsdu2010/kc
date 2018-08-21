package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.KafkaClusterCfg;
import com.sf.shiva.oms.ht.domain.KafkaClusterCfgExample;

/**
 * KafkaClusterCfgManager类
 *
 * @author 01369626
*/
public interface KafkaClusterCfgManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(KafkaClusterCfgExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(KafkaClusterCfgExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param id 
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
    int insert(KafkaClusterCfg record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(KafkaClusterCfg record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<KafkaClusterCfg> 记录列表
	 *
    */
    List<KafkaClusterCfg> selectByExample(KafkaClusterCfgExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(KafkaClusterCfg record, KafkaClusterCfgExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(KafkaClusterCfg record,  KafkaClusterCfgExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(KafkaClusterCfg record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(KafkaClusterCfg record);
	
}