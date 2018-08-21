package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.domain.SqlCfgExample;
import com.sf.shiva.oms.ht.mapper.SqlCfgMapper;
import com.sf.shiva.oms.ht.manager.SqlCfgManager;

/**
 * SqlCfgManager实现类
 *
 * @author 01369626
*/
@Component
public class SqlCfgManagerImpl implements SqlCfgManager {

	@Autowired
	private SqlCfgMapper sqlCfgMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(SqlCfgExample example){
		return sqlCfgMapper.countByExample(example);
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
    public int deleteByExample(SqlCfgExample example){
		return sqlCfgMapper.deleteByExample(example);
	}
	
	/**
	 * 按主键删除
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByPrimaryKey(String sqlId){
		return sqlCfgMapper.deleteByPrimaryKey(sqlId);
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
    public int insert(SqlCfg record){
	    return sqlCfgMapper.insert(record);
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
    public int insertSelective(SqlCfg record){
		return sqlCfgMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<SqlCfg> 记录列表
	 *
    */
	@Override
    public List<SqlCfg> selectByExample(SqlCfgExample example){
		return sqlCfgMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return SqlCfg 记录 
	 *
    */
	@Override
    public SqlCfg selectByPrimaryKey(String sqlId){
		return sqlCfgMapper.selectByPrimaryKey(sqlId);
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
    public int updateByExampleSelective(SqlCfg record, SqlCfgExample example){
		return sqlCfgMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(SqlCfg record,  SqlCfgExample example){
		return sqlCfgMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(SqlCfg record){
		return sqlCfgMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(SqlCfg record){
		return sqlCfgMapper.updateByPrimaryKey(record);
	}
	
}