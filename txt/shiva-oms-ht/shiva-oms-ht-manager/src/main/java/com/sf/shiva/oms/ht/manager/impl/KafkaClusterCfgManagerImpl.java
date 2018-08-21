package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.KafkaClusterCfg;
import com.sf.shiva.oms.ht.domain.KafkaClusterCfgExample;
import com.sf.shiva.oms.ht.mapper.KafkaClusterCfgMapper;
import com.sf.shiva.oms.ht.manager.KafkaClusterCfgManager;

/**
 * KafkaClusterCfgManager实现类
 *
 * @author 01369626
*/
@Component
public class KafkaClusterCfgManagerImpl implements KafkaClusterCfgManager {

	@Autowired
	private KafkaClusterCfgMapper kafkaClusterCfgMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(KafkaClusterCfgExample example){
		return kafkaClusterCfgMapper.countByExample(example);
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
    public int deleteByExample(KafkaClusterCfgExample example){
		return kafkaClusterCfgMapper.deleteByExample(example);
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
		return kafkaClusterCfgMapper.deleteByPrimaryKey(id);
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
    public int insert(KafkaClusterCfg record){
	    return kafkaClusterCfgMapper.insert(record);
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
    public int insertSelective(KafkaClusterCfg record){
		return kafkaClusterCfgMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<KafkaClusterCfg> 记录列表
	 *
    */
	@Override
    public List<KafkaClusterCfg> selectByExample(KafkaClusterCfgExample example){
		return kafkaClusterCfgMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return KafkaClusterCfg 记录 
	 *
    */
	@Override
    public KafkaClusterCfg selectByPrimaryKey(String id){
		return kafkaClusterCfgMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(KafkaClusterCfg record, KafkaClusterCfgExample example){
		return kafkaClusterCfgMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(KafkaClusterCfg record,  KafkaClusterCfgExample example){
		return kafkaClusterCfgMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(KafkaClusterCfg record){
		return kafkaClusterCfgMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(KafkaClusterCfg record){
		return kafkaClusterCfgMapper.updateByPrimaryKey(record);
	}
	
}