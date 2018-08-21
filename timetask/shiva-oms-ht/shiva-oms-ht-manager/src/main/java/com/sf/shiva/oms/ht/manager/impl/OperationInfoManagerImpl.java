package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sf.shiva.oms.ht.domain.OperationInfoKey;
import com.sf.shiva.oms.ht.domain.OperationInfo;
import com.sf.shiva.oms.ht.domain.OperationInfoExample;
import com.sf.shiva.oms.ht.mapper.OperationInfoMapper;
import com.sf.shiva.oms.ht.manager.OperationInfoManager;

/**
 * OperationInfoManager实现类
 *
 * @author 01369626
*/
@Component
public class OperationInfoManagerImpl implements OperationInfoManager {

	@Autowired
	private OperationInfoMapper operationInfoMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(OperationInfoExample example){
		return operationInfoMapper.countByExample(example);
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
    public int deleteByExample(OperationInfoExample example){
		return operationInfoMapper.deleteByExample(example);
	}
	
	/**
	 * 按主键删除
	 * 
	 * @param key 主键
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByPrimaryKey(OperationInfoKey key){
		return operationInfoMapper.deleteByPrimaryKey(key);
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
    public int insert(OperationInfo record){
	    return operationInfoMapper.insert(record);
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
    public int insertSelective(OperationInfo record){
		return operationInfoMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<OperationInfo> 记录列表
	 *
    */
	@Override
    public List<OperationInfo> selectByExample(OperationInfoExample example){
		return operationInfoMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param key 主键
	 * 
	 * @return OperationInfo 记录 
	 *
    */
	@Override
    public OperationInfo selectByPrimaryKey(OperationInfoKey key){
		return operationInfoMapper.selectByPrimaryKey(key);
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
    public int updateByExampleSelective(OperationInfo record, OperationInfoExample example){
		return operationInfoMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(OperationInfo record,  OperationInfoExample example){
		return operationInfoMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    public int updateByPrimaryKeySelective(OperationInfo record){
		return operationInfoMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(OperationInfo record){
		return operationInfoMapper.updateByPrimaryKey(record);
	}
	
}