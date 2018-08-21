package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.KafkaWriteManager;
import com.sf.shiva.oms.ht.domain.KafkaWriteManagerExample;
import com.sf.shiva.oms.ht.mapper.KafkaWriteManagerMapper;
import com.sf.shiva.oms.ht.manager.KafkaWriteManagerManager;

/**
 * KafkaWriteManagerManager实现类
 *
 * @author 01369626
*/
@Component
public class KafkaWriteManagerManagerImpl implements KafkaWriteManagerManager {

	@Autowired
	private KafkaWriteManagerMapper kafkaWriteManagerMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(KafkaWriteManagerExample example){
		return kafkaWriteManagerMapper.countByExample(example);
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
    public int deleteByExample(KafkaWriteManagerExample example){
		return kafkaWriteManagerMapper.deleteByExample(example);
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
		return kafkaWriteManagerMapper.deleteByPrimaryKey(id);
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
    public int insert(KafkaWriteManager record){
	    return kafkaWriteManagerMapper.insert(record);
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
    public int insertSelective(KafkaWriteManager record){
		return kafkaWriteManagerMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<KafkaWriteManager> 记录列表
	 *
    */
	@Override
    public List<KafkaWriteManager> selectByExample(KafkaWriteManagerExample example){
		return kafkaWriteManagerMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return KafkaWriteManager 记录 
	 *
    */
	@Override
    public KafkaWriteManager selectByPrimaryKey(String id){
		return kafkaWriteManagerMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(KafkaWriteManager record, KafkaWriteManagerExample example){
		return kafkaWriteManagerMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(KafkaWriteManager record,  KafkaWriteManagerExample example){
		return kafkaWriteManagerMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(KafkaWriteManager record){
		return kafkaWriteManagerMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(KafkaWriteManager record){
		return kafkaWriteManagerMapper.updateByPrimaryKey(record);
	}
	
}