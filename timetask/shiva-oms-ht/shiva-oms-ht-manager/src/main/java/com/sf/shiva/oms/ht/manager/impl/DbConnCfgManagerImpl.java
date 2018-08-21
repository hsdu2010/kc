package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.DbConnCfgExample;
import com.sf.shiva.oms.ht.mapper.DbConnCfgMapper;
import com.sf.shiva.oms.ht.manager.DbConnCfgManager;

/**
 * DbConnCfgManager实现类
 *
 * @author 01369626
*/
@Component
public class DbConnCfgManagerImpl implements DbConnCfgManager {

	@Autowired
	private DbConnCfgMapper dbConnCfgMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(DbConnCfgExample example){
		return dbConnCfgMapper.countByExample(example);
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
    public int deleteByExample(DbConnCfgExample example){
		return dbConnCfgMapper.deleteByExample(example);
	}
	
	/**
	 * 按主键删除
	 * 
	 * @param dbId id
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByPrimaryKey(String dbId){
		return dbConnCfgMapper.deleteByPrimaryKey(dbId);
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
    public int insert(DbConnCfg record){
	    return dbConnCfgMapper.insert(record);
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
    public int insertSelective(DbConnCfg record){
		return dbConnCfgMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<DbConnCfg> 记录列表
	 *
    */
	@Override
    public List<DbConnCfg> selectByExample(DbConnCfgExample example){
		return dbConnCfgMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param dbId id
	 * 
	 * @return DbConnCfg 记录 
	 *
    */
	@Override
    public DbConnCfg selectByPrimaryKey(String dbId){
		return dbConnCfgMapper.selectByPrimaryKey(dbId);
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
    public int updateByExampleSelective(DbConnCfg record, DbConnCfgExample example){
		return dbConnCfgMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(DbConnCfg record,  DbConnCfgExample example){
		return dbConnCfgMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(DbConnCfg record){
		return dbConnCfgMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(DbConnCfg record){
		return dbConnCfgMapper.updateByPrimaryKey(record);
	}
	
}