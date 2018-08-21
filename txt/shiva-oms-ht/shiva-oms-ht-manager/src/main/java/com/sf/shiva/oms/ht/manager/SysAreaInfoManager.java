package com.sf.shiva.oms.ht.manager;

import java.util.List;

import com.sf.shiva.oms.ht.domain.SysAreaInfo;
import com.sf.shiva.oms.ht.domain.SysAreaInfoExample;


/**
 * AreaManager类
 *
 * @author 01139932
*/
public interface SysAreaInfoManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(SysAreaInfoExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(SysAreaInfoExample example);
	
	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByPrimaryKey(Long id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insert(SysAreaInfo record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(SysAreaInfo record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<Area> 记录列表
	 *
    */
    List<SysAreaInfo> selectByExample(SysAreaInfoExample example);

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return Area 记录 
	 *
    */
    SysAreaInfo selectByPrimaryKey(Long id);

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(SysAreaInfo record, SysAreaInfoExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(SysAreaInfo record,  SysAreaInfoExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(SysAreaInfo record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(SysAreaInfo record);
    
    int updateToDeleteStatus(Long id);
    
    public List<SysAreaInfo> getAreaList();
	
}