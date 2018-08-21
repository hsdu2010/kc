package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.KafkaReadManager;
import com.sf.shiva.oms.ht.domain.KafkaReadManagerExample;

/**
 * KafkaReadManagerManager类
 *
 * @author 01369626
*/
public interface KafkaReadManagerManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(KafkaReadManagerExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(KafkaReadManagerExample example);
	
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
    int insert(KafkaReadManager record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(KafkaReadManager record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<KafkaReadManager> 记录列表
	 *
    */
    List<KafkaReadManager> selectByExample(KafkaReadManagerExample example);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return KafkaReadManager 记录 
	 *
    */
    KafkaReadManager selectByPrimaryKey(String id);

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(KafkaReadManager record, KafkaReadManagerExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(KafkaReadManager record,  KafkaReadManagerExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(KafkaReadManager record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(KafkaReadManager record);
	
}