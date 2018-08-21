package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.KafkaReadManager;
import com.sf.shiva.oms.ht.domain.KafkaReadManagerExample;
import com.sf.shiva.oms.ht.mapper.KafkaReadManagerMapper;
import com.sf.shiva.oms.ht.manager.KafkaReadManagerManager;

/**
 * KafkaReadManagerManager实现类
 *
 * @author 01369626
*/
@Component
public class KafkaReadManagerManagerImpl implements KafkaReadManagerManager {

	@Autowired
	private KafkaReadManagerMapper kafkaReadManagerMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(KafkaReadManagerExample example){
		return kafkaReadManagerMapper.countByExample(example);
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
    public int deleteByExample(KafkaReadManagerExample example){
		return kafkaReadManagerMapper.deleteByExample(example);
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
		return kafkaReadManagerMapper.deleteByPrimaryKey(id);
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
    public int insert(KafkaReadManager record){
	    return kafkaReadManagerMapper.insert(record);
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
    public int insertSelective(KafkaReadManager record){
		return kafkaReadManagerMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<KafkaReadManager> 记录列表
	 *
    */
	@Override
    public List<KafkaReadManager> selectByExample(KafkaReadManagerExample example){
		return kafkaReadManagerMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return KafkaReadManager 记录 
	 *
    */
	@Override
    public KafkaReadManager selectByPrimaryKey(String id){
		return kafkaReadManagerMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(KafkaReadManager record, KafkaReadManagerExample example){
		return kafkaReadManagerMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(KafkaReadManager record,  KafkaReadManagerExample example){
		return kafkaReadManagerMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(KafkaReadManager record){
		return kafkaReadManagerMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(KafkaReadManager record){
		return kafkaReadManagerMapper.updateByPrimaryKey(record);
	}
	
}