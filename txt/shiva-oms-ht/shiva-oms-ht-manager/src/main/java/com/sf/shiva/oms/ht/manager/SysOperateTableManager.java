package com.sf.shiva.oms.ht.manager;

import java.util.List;
import com.sf.shiva.oms.ht.domain.SysOperateTable;
import com.sf.shiva.oms.ht.domain.SysOperateTableExample;
import com.sf.shiva.oms.ht.domain.SysRoleTableInfo;

/**
 * OperateTableManager类
 *
 * @author 01139932
*/
public interface SysOperateTableManager {
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
    int countByExample(SysOperateTableExample example);

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
    int deleteByExample(SysOperateTableExample example);
	
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
    int insert(SysOperateTable record);
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
    int insertSelective(SysOperateTable record);
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<OperateTable> 记录列表
	 *
    */
    List<SysOperateTable> selectByExample(SysOperateTableExample example);

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
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExampleSelective(SysOperateTable record, SysOperateTableExample example);

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
    int updateByExample(SysOperateTable record,  SysOperateTableExample example);

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKeySelective(SysOperateTable record);


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
    int updateByPrimaryKey(SysOperateTable record);
    
    int updateToDeleteStatus(Long id);
    
    List<SysRoleTableInfo> getRoleTableList(String roleId);
    
    int changeUserRole(String tableId, Boolean state, String roleId);
	
}