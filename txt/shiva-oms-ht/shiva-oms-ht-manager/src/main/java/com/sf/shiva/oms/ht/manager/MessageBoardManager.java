package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.MessageBoard;
import com.sf.shiva.oms.ht.domain.MessageBoardExample;

/**
 * MessageBoardManager类
 *
 * @author 01369626
*/
public interface MessageBoardManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(MessageBoardExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(MessageBoardExample example);
	
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
    int insert(MessageBoard record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(MessageBoard record);
	
	/**
	 * 按条件查询（包括BLOB字段）
	 * 
	 * @param example 条件
	 * 
	 * @return  List<MessageBoard> 记录列表 
	 *
    */
    List<MessageBoard> selectByExampleWithBLOBs(MessageBoardExample example);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<MessageBoard> 记录列表
	 *
    */
    List<MessageBoard> selectByExample(MessageBoardExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(MessageBoard record, MessageBoardExample example);

	/**
	 * 按条件更新（包括BLOB字段）
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleWithBLOBs(MessageBoard record, MessageBoardExample example);
	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(MessageBoard record,  MessageBoardExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(MessageBoard record);

	/**
	 * 按主键更新（包括BLOB字段）
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeyWithBLOBs(MessageBoard record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(MessageBoard record);
	
}