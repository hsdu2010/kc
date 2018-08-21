package com.sf.shiva.oms.ht.service;

import java.io.InputStream;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.UploadjarManager;
import com.sf.shiva.oms.ht.domain.UploadjarManagerExample;
import com.sf.shiva.oms.ht.exception.BusinessException;

/**
 * UploadjarManagerService类
 *
 * @author 01369626
*/
public interface UploadjarManagerService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(UploadjarManagerExample example);

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
    Integer insert(UploadjarManager record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<UploadjarManager> 记录列表 
	 *
    */
    Page<UploadjarManager> selectByExample(UploadjarManagerExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return UploadjarManager 记录 
	 *
    */
	UploadjarManager selectByPrimaryKey(String id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(UploadjarManager record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(UploadjarManager record);
    
    /**
     * 添加记录
     * */
    Integer insert(UploadjarManager record, InputStream src) throws BusinessException ;
    
    /**
     * 删除记录并且删除Jar包
     * **/
    Integer deleteByPrimaryKey(String id, String jarName);
	
}