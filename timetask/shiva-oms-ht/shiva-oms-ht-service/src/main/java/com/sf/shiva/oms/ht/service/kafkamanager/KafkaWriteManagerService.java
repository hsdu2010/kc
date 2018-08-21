package com.sf.shiva.oms.ht.service.kafkamanager;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.KafkaWriteManager;
import com.sf.shiva.oms.ht.domain.KafkaWriteManagerExample;

/**
 * KafkaWriteManagerService类
 *
 * @author 01369626
*/
public interface KafkaWriteManagerService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(KafkaWriteManagerExample example);

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
    Integer insert(KafkaWriteManager record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<KafkaWriteManager> 记录列表 
	 *
    */
    Page<KafkaWriteManager> selectByExample(KafkaWriteManagerExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return KafkaWriteManager 记录 
	 *
    */
	KafkaWriteManager selectByPrimaryKey(String id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(KafkaWriteManager record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(KafkaWriteManager record);
    
    public Page<KafkaWriteManager> searchByName(String name, Integer pageNum, Integer pageSize);
	
}