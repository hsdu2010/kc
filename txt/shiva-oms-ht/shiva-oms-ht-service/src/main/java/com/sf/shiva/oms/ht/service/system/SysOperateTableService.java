package com.sf.shiva.oms.ht.service.system;

import java.util.List;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.SysOperateTable;
import com.sf.shiva.oms.ht.domain.SysOperateTableExample;
import com.sf.shiva.oms.ht.domain.SysRoleTableInfo;

/**
 * OperateTableService类
 *
 * @author 01139932
*/
public interface SysOperateTableService {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	Integer countByExample(SysOperateTableExample example);

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
    Integer insert(SysOperateTable record);
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page<OperateTable> 记录列表 
	 *
    */
    Page<SysOperateTable> selectByExample(SysOperateTableExample example, Integer pageNum, Integer pageSize);

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return OperateTable 记录 
	 *
    */
	SysOperateTable selectByPrimaryKey(Long id);
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
	Integer updateByPrimaryKeySelective(SysOperateTable record);

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    Integer updateByPrimaryKey(SysOperateTable record);
    
    List<SysRoleTableInfo> getRoleTableList(String roleId);
    
    int changeRoleTable(String tableId, Boolean state, String roleId);
    
    Integer updateToDeleteStatus(Long id);
	
}