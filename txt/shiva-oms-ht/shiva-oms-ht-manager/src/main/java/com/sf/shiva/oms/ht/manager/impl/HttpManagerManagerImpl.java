package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.HttpManager;
import com.sf.shiva.oms.ht.domain.HttpManagerExample;
import com.sf.shiva.oms.ht.mapper.HttpManagerMapper;
import com.sf.shiva.oms.ht.manager.HttpManagerManager;

/**
 * HttpManagerManager实现类
 *
 * @author 01369626
*/
@Component
public class HttpManagerManagerImpl implements HttpManagerManager {

	@Autowired
	private HttpManagerMapper httpManagerMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(HttpManagerExample example){
		return httpManagerMapper.countByExample(example);
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
    public int deleteByExample(HttpManagerExample example){
		return httpManagerMapper.deleteByExample(example);
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
		return httpManagerMapper.deleteByPrimaryKey(id);
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
    public int insert(HttpManager record){
	    return httpManagerMapper.insert(record);
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
    public int insertSelective(HttpManager record){
		return httpManagerMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<HttpManager> 记录列表
	 *
    */
	@Override
    public List<HttpManager> selectByExample(HttpManagerExample example){
		return httpManagerMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return HttpManager 记录 
	 *
    */
	@Override
    public HttpManager selectByPrimaryKey(String id){
		return httpManagerMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(HttpManager record, HttpManagerExample example){
		return httpManagerMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(HttpManager record,  HttpManagerExample example){
		return httpManagerMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(HttpManager record){
		return httpManagerMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(HttpManager record){
		return httpManagerMapper.updateByPrimaryKey(record);
	}
	
}