package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.RedisCfg;
import com.sf.shiva.oms.ht.domain.RedisCfgExample;
import com.sf.shiva.oms.ht.mapper.RedisCfgMapper;
import com.sf.shiva.oms.ht.manager.RedisCfgManager;

/**
 * RedisCfgManager实现类
 *
 * @author 01369626
*/
@Component
public class RedisCfgManagerImpl implements RedisCfgManager {

	@Autowired
	private RedisCfgMapper redisCfgMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(RedisCfgExample example){
		return redisCfgMapper.countByExample(example);
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
    public int deleteByExample(RedisCfgExample example){
		return redisCfgMapper.deleteByExample(example);
	}
	
	/**
	 * 按主键删除
	 * 
	 * @param id id
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByPrimaryKey(String id){
		return redisCfgMapper.deleteByPrimaryKey(id);
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
    public int insert(RedisCfg record){
	    return redisCfgMapper.insert(record);
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
    public int insertSelective(RedisCfg record){
		return redisCfgMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<RedisCfg> 记录列表
	 *
    */
	@Override
    public List<RedisCfg> selectByExample(RedisCfgExample example){
		return redisCfgMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id id
	 * 
	 * @return RedisCfg 记录 
	 *
    */
	@Override
    public RedisCfg selectByPrimaryKey(String id){
		return redisCfgMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(RedisCfg record, RedisCfgExample example){
		return redisCfgMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(RedisCfg record,  RedisCfgExample example){
		return redisCfgMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(RedisCfg record){
		return redisCfgMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(RedisCfg record){
		return redisCfgMapper.updateByPrimaryKey(record);
	}
	
}