package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.MessageBoard;
import com.sf.shiva.oms.ht.domain.MessageBoardExample;
import com.sf.shiva.oms.ht.mapper.MessageBoardMapper;
import com.sf.shiva.oms.ht.manager.MessageBoardManager;

/**
 * MessageBoardManager实现类
 *
 * @author 01369626
*/
@Component
public class MessageBoardManagerImpl implements MessageBoardManager {

	@Autowired
	private MessageBoardMapper messageBoardMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(MessageBoardExample example){
		return messageBoardMapper.countByExample(example);
	}

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByExample(MessageBoardExample example){
		return messageBoardMapper.deleteByExample(example);
	}
	
	/**
	 * 按主键删除
	 * 
	 * @param id 
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByPrimaryKey(String id){
		return messageBoardMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int insert(MessageBoard record){
	    return messageBoardMapper.insert(record);
	}
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int insertSelective(MessageBoard record){
		return messageBoardMapper.insertSelective(record);
	}
	
	/**
	 * 按条件查询（包括BLOB字段）
	 * 
	 * @param example 条件
	 * 
	 * @return  List<MessageBoard> 记录列表 
	 *
    */
	@Override
    public List<MessageBoard> selectByExampleWithBLOBs(MessageBoardExample example){
		return messageBoardMapper.selectByExampleWithBLOBs(example);
	}
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<MessageBoard> 记录列表
	 *
    */
	@Override
    public List<MessageBoard> selectByExample(MessageBoardExample example){
		return messageBoardMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return MessageBoard 记录 
	 *
    */
	@Override
    public MessageBoard selectByPrimaryKey(String id){
		return messageBoardMapper.selectByPrimaryKey(id);
	}

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
	@Override
    public int updateByExampleSelective(MessageBoard record, MessageBoardExample example){
		return messageBoardMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 按条件更新（包括BLOB字段）
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
	@Override
    public int updateByExampleWithBLOBs(MessageBoard record, MessageBoardExample example){
		return messageBoardMapper.updateByExampleSelective(record, example);
	}
	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
	@Override
    public int updateByExample(MessageBoard record,  MessageBoardExample example){
		return messageBoardMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(MessageBoard record){
		return messageBoardMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 按主键更新（包括BLOB字段）
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeyWithBLOBs(MessageBoard record){
		return messageBoardMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(MessageBoard record){
		return messageBoardMapper.updateByPrimaryKey(record);
	}
	
}