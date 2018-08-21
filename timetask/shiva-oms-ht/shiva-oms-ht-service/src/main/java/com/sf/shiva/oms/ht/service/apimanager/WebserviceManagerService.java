package com.sf.shiva.oms.ht.service.apimanager;


import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.WebserviceManager;
import com.sf.shiva.oms.ht.domain.WebserviceManagerExample;

/**
 * WebserviceManagerService类
 *
 * @author 01369626
*/
public interface WebserviceManagerService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(WebserviceManagerExample example);

	/**
	 * 按主键删除
	 * 
	 * @param id 
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer deleteByPrimaryKey(String id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer insert(WebserviceManager record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<WebserviceManager> 记录列表 
	 *
    */
    Page<WebserviceManager> selectByExample(WebserviceManagerExample example, Integer pageNum, Integer pageSize);

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
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(WebserviceManager record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(WebserviceManager record);
	
    public Page<WebserviceManager> searchByName(String name, Integer pageNum, Integer pageSize);
  
}