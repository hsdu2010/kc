package com.sf.shiva.oms.ht.service.apimanager;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.HttpManager;
import com.sf.shiva.oms.ht.domain.HttpManagerExample;


/**
 * UserHttpInfoService类
 *
 * @author 01369626
*/
public interface HttpManagerService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(HttpManagerExample example);

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
    Integer insert(HttpManager record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<UserHttpInfo> 记录列表 
	 *
    */
    Page<HttpManager> selectByExample(HttpManagerExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return UserHttpInfo 记录 
	 *
    */
    HttpManager selectByPrimaryKey(String id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(HttpManager record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(HttpManager record);
	
    /**
     * 根据remark进行模糊查询
     * @param remark 查询关键字
     * @param pageNum 页码
     * @param pageSize 每页记录条数
     * @return 查询结果
     * @author 01369626
     * @date 2017年12月4日
     */
    Page<HttpManager> searchByRemark(String remark, Integer pageNum, Integer pageSize);
}