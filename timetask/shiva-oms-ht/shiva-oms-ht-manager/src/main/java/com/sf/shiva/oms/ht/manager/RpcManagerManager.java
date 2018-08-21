package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.domain.RpcManagerExample;

/**
 * RpcManagerManager类
 *
 * @author 01369626
*/
public interface RpcManagerManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(RpcManagerExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(RpcManagerExample example);
	
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
    int insert(RpcManager record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(RpcManager record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<RpcManager> 记录列表
	 *
    */
    List<RpcManager> selectByExample(RpcManagerExample example);

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return RpcManager 记录 
	 *
    */
    RpcManager selectByPrimaryKey(String id);

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(RpcManager record, RpcManagerExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(RpcManager record,  RpcManagerExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(RpcManager record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(RpcManager record);
	
}