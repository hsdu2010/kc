package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.WebserviceManager;
import com.sf.shiva.oms.ht.domain.WebserviceManagerExample;

/**
 * WebserviceManagerManager类
 *
 * @author 01369626
*/
public interface WebserviceManagerManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(WebserviceManagerExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(WebserviceManagerExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param id 
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(String id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(WebserviceManager record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(WebserviceManager record);
	
	/**
	 * 按条件查询（包括BLOB字段）
	 * 
	 * @param example 条件
	 * 
	 * @return  List<WebserviceManager> 记录列表 
	 *
    */
    List<WebserviceManager> selectByExampleWithBLOBs(WebserviceManagerExample example);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<WebserviceManager> 记录列表
	 *
    */
    List<WebserviceManager> selectByExample(WebserviceManagerExample example);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return WebserviceManager 记录 
	 *
    */
    WebserviceManager selectByPrimaryKey(String id);

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(WebserviceManager record, WebserviceManagerExample example);

	/**
	 * 按条件更新（包括BLOB字段）
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleWithBLOBs(WebserviceManager record, WebserviceManagerExample example);
	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(WebserviceManager record,  WebserviceManagerExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(WebserviceManager record);

	/**
	 * 按主键更新（包括BLOB字段）
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeyWithBLOBs(WebserviceManager record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(WebserviceManager record);
	
}