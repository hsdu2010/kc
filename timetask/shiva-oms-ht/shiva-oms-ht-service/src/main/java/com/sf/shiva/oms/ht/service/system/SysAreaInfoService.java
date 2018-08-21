package com.sf.shiva.oms.ht.service.system;

import java.util.List;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.ResourceTreeNode;
import com.sf.shiva.oms.ht.domain.SysAreaInfo;
import com.sf.shiva.oms.ht.domain.SysAreaInfoExample;

/**
 * AreaService类
 *
 * @author 01139932
*/
public interface SysAreaInfoService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(SysAreaInfoExample example);

	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer deleteByPrimaryKey(Long id);

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    Integer insert(SysAreaInfo record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<Area> 记录列表 
	 *
    */
    Page<SysAreaInfo> selectByExample(SysAreaInfoExample example, Integer pageNum, Integer pageSize);

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
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(SysAreaInfo record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(SysAreaInfo record);
    
    Integer updateToDeleteStatus(Long id);
    
    public List<ResourceTreeNode> getAreaList();
    
	
}