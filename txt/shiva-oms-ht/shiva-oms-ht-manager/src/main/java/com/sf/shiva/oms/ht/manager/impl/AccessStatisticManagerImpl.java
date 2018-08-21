package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sf.shiva.oms.ht.domain.AccessStatisticKey;
import com.sf.shiva.oms.ht.domain.AccessStatistic;
import com.sf.shiva.oms.ht.domain.AccessStatisticExample;
import com.sf.shiva.oms.ht.mapper.AccessStatisticMapper;
import com.sf.shiva.oms.ht.manager.AccessStatisticManager;

/**
 * AccessStatisticManager实现类
 *
 * @author 01369626
*/
@Component
public class AccessStatisticManagerImpl implements AccessStatisticManager {

	@Autowired
	private AccessStatisticMapper accessStatisticMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(AccessStatisticExample example){
		return accessStatisticMapper.countByExample(example);
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
    public int deleteByExample(AccessStatisticExample example){
		return accessStatisticMapper.deleteByExample(example);
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
    public int deleteByPrimaryKey(AccessStatisticKey key){
		return accessStatisticMapper.deleteByPrimaryKey(key);
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
    public int insert(AccessStatistic record){
	    return accessStatisticMapper.insert(record);
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
    public int insertSelective(AccessStatistic record){
		return accessStatisticMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<AccessStatistic> 记录列表
	 *
    */
	@Override
    public List<AccessStatistic> selectByExample(AccessStatisticExample example){
		return accessStatisticMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param key 主键
	 * 
	 * @return AccessStatistic 记录 
	 *
    */
	@Override
    public AccessStatistic selectByPrimaryKey(AccessStatisticKey key){
		return accessStatisticMapper.selectByPrimaryKey(key);
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
    public int updateByExampleSelective(AccessStatistic record, AccessStatisticExample example){
		return accessStatisticMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(AccessStatistic record,  AccessStatisticExample example){
		return accessStatisticMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    public int updateByPrimaryKeySelective(AccessStatistic record){
		return accessStatisticMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(AccessStatistic record){
		return accessStatisticMapper.updateByPrimaryKey(record);
	}
	
}