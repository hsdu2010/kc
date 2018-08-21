package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfg;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfgExample;
import com.sf.shiva.oms.ht.mapper.HbaseQueryCfgMapper;
import com.sf.shiva.oms.ht.manager.HbaseQueryCfgManager;

/**
 * HbaseQueryCfgManager实现类
 *
 * @author 01369626
*/
@Component
public class HbaseQueryCfgManagerImpl implements HbaseQueryCfgManager {

	@Autowired
	private HbaseQueryCfgMapper hbaseQueryCfgMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(HbaseQueryCfgExample example){
		return hbaseQueryCfgMapper.countByExample(example);
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
    public int deleteByExample(HbaseQueryCfgExample example){
		return hbaseQueryCfgMapper.deleteByExample(example);
	}
	
	/**
	 * 按主键删除
	 * 
	 * @param id 主键
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByPrimaryKey(String id){
		return hbaseQueryCfgMapper.deleteByPrimaryKey(id);
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
    public int insert(HbaseQueryCfg record){
	    return hbaseQueryCfgMapper.insert(record);
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
    public int insertSelective(HbaseQueryCfg record){
		return hbaseQueryCfgMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<HbaseQueryCfg> 记录列表
	 *
    */
	@Override
    public List<HbaseQueryCfg> selectByExample(HbaseQueryCfgExample example){
		return hbaseQueryCfgMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 主键
	 * 
	 * @return HbaseQueryCfg 记录 
	 *
    */
	@Override
    public HbaseQueryCfg selectByPrimaryKey(String id){
		return hbaseQueryCfgMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(HbaseQueryCfg record, HbaseQueryCfgExample example){
		return hbaseQueryCfgMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(HbaseQueryCfg record,  HbaseQueryCfgExample example){
		return hbaseQueryCfgMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    public int updateByPrimaryKeySelective(HbaseQueryCfg record){
		return hbaseQueryCfgMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(HbaseQueryCfg record){
		return hbaseQueryCfgMapper.updateByPrimaryKey(record);
	}
	
}