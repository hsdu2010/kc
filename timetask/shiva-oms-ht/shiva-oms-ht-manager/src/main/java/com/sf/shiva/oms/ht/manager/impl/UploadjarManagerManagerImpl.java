package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.UploadjarManager;
import com.sf.shiva.oms.ht.domain.UploadjarManagerExample;
import com.sf.shiva.oms.ht.mapper.UploadjarManagerMapper;
import com.sf.shiva.oms.ht.manager.UploadjarManagerManager;

/**
 * UploadjarManagerManager实现类
 *
 * @author 01369626
*/
@Component
public class UploadjarManagerManagerImpl implements UploadjarManagerManager {

	@Autowired
	private UploadjarManagerMapper uploadjarManagerMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(UploadjarManagerExample example){
		return uploadjarManagerMapper.countByExample(example);
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
    public int deleteByExample(UploadjarManagerExample example){
		return uploadjarManagerMapper.deleteByExample(example);
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
		return uploadjarManagerMapper.deleteByPrimaryKey(id);
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
    public int insert(UploadjarManager record){
	    return uploadjarManagerMapper.insert(record);
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
    public int insertSelective(UploadjarManager record){
		return uploadjarManagerMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<UploadjarManager> 记录列表
	 *
    */
	@Override
    public List<UploadjarManager> selectByExample(UploadjarManagerExample example){
		return uploadjarManagerMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return UploadjarManager 记录 
	 *
    */
	@Override
    public UploadjarManager selectByPrimaryKey(String id){
		return uploadjarManagerMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(UploadjarManager record, UploadjarManagerExample example){
		return uploadjarManagerMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(UploadjarManager record,  UploadjarManagerExample example){
		return uploadjarManagerMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(UploadjarManager record){
		return uploadjarManagerMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(UploadjarManager record){
		return uploadjarManagerMapper.updateByPrimaryKey(record);
	}
	
}