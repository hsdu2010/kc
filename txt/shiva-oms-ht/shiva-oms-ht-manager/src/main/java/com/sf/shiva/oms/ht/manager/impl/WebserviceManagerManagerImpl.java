package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.WebserviceManager;
import com.sf.shiva.oms.ht.domain.WebserviceManagerExample;
import com.sf.shiva.oms.ht.mapper.WebserviceManagerMapper;
import com.sf.shiva.oms.ht.manager.WebserviceManagerManager;

/**
 * WebserviceManagerManager实现类
 *
 * @author 01369626
*/
@Component
public class WebserviceManagerManagerImpl implements WebserviceManagerManager {

	@Autowired
	private WebserviceManagerMapper webserviceManagerMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(WebserviceManagerExample example){
		return webserviceManagerMapper.countByExample(example);
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
    public int deleteByExample(WebserviceManagerExample example){
		return webserviceManagerMapper.deleteByExample(example);
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
		return webserviceManagerMapper.deleteByPrimaryKey(id);
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
    public int insert(WebserviceManager record){
	    return webserviceManagerMapper.insert(record);
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
    public int insertSelective(WebserviceManager record){
		return webserviceManagerMapper.insertSelective(record);
	}
	
	/**
	 * 按条件查询（包括BLOB字段）
	 * 
	 * @param example 条件
	 * 
	 * @return  List<WebserviceManager> 记录列表 
	 *
    */
	@Override
    public List<WebserviceManager> selectByExampleWithBLOBs(WebserviceManagerExample example){
		return webserviceManagerMapper.selectByExampleWithBLOBs(example);
	}
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<WebserviceManager> 记录列表
	 *
    */
	@Override
    public List<WebserviceManager> selectByExample(WebserviceManagerExample example){
		return webserviceManagerMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return WebserviceManager 记录 
	 *
    */
	@Override
    public WebserviceManager selectByPrimaryKey(String id){
		return webserviceManagerMapper.selectByPrimaryKey(id);
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
    public int updateByExampleSelective(WebserviceManager record, WebserviceManagerExample example){
		return webserviceManagerMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 按条件更新（包括BLOB字段）
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
	@Override
    public int updateByExampleWithBLOBs(WebserviceManager record, WebserviceManagerExample example){
		return webserviceManagerMapper.updateByExampleSelective(record, example);
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
    public int updateByExample(WebserviceManager record,  WebserviceManagerExample example){
		return webserviceManagerMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(WebserviceManager record){
		return webserviceManagerMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 按主键更新（包括BLOB字段）
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeyWithBLOBs(WebserviceManager record){
		return webserviceManagerMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(WebserviceManager record){
		return webserviceManagerMapper.updateByPrimaryKey(record);
	}
	
}