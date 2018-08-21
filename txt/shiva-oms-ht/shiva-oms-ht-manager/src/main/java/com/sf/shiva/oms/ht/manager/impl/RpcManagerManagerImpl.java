package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.domain.RpcManagerExample;
import com.sf.shiva.oms.ht.mapper.RpcManagerMapper;
import com.sf.shiva.oms.ht.manager.RpcManagerManager;

/**
 * RpcManagerManager实现类
 *
 * @author 01369626
*/
@Component
public class RpcManagerManagerImpl implements RpcManagerManager {

	@Autowired
	private RpcManagerMapper rpcManagerMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(RpcManagerExample example){
		return rpcManagerMapper.countByExample(example);
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
    public int deleteByExample(RpcManagerExample example){
		return rpcManagerMapper.deleteByExample(example);
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
		return rpcManagerMapper.deleteByPrimaryKey(id);
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
    public int insert(RpcManager record){
	    return rpcManagerMapper.insert(record);
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
    public int insertSelective(RpcManager record){
		return rpcManagerMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<RpcManager> 记录列表
	 *
    */
	@Override
    public List<RpcManager> selectByExample(RpcManagerExample example){
		return rpcManagerMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return RpcManager 记录 
	 *
    */
	@Override
    public RpcManager selectByPrimaryKey(String id){
		return rpcManagerMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(RpcManager record, RpcManagerExample example){
		return rpcManagerMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(RpcManager record,  RpcManagerExample example){
		return rpcManagerMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(RpcManager record){
		return rpcManagerMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(RpcManager record){
		return rpcManagerMapper.updateByPrimaryKey(record);
	}
	
}