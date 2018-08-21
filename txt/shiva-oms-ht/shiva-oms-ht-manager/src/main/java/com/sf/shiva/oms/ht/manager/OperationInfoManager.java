package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.OperationInfoKey;
import com.sf.shiva.oms.ht.domain.OperationInfo;
import com.sf.shiva.oms.ht.domain.OperationInfoExample;

/**
 * OperationInfoManager类
 *
 * @author 01369626
*/
public interface OperationInfoManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(OperationInfoExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(OperationInfoExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param key 主键
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(OperationInfoKey key);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(OperationInfo record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(OperationInfo record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<OperationInfo> 记录列表
	 *
    */
    List<OperationInfo> selectByExample(OperationInfoExample example);

	/**
	 * 按主键查询
	 * 
	 * @param key 主键
	 * 
	 * @return OperationInfo 记录 
	 *
    */
    OperationInfo selectByPrimaryKey(OperationInfoKey key);

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(OperationInfo record, OperationInfoExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(OperationInfo record,  OperationInfoExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(OperationInfo record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(OperationInfo record);
	
}