package com.sf.shiva.oms.ht.service;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.MessageBoard;
import com.sf.shiva.oms.ht.domain.MessageBoardExample;

/**
 * MessageBoardService类
 *
 * @author 01369626
*/
public interface MessageBoardService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(MessageBoardExample example);

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
    Integer insert(MessageBoard record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<MessageBoard> 记录列表 
	 *
    */
    Page<MessageBoard> selectByExample(MessageBoardExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return MessageBoard 记录 
	 *
    */
	MessageBoard selectByPrimaryKey(String id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(MessageBoard record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(MessageBoard record);
	
}