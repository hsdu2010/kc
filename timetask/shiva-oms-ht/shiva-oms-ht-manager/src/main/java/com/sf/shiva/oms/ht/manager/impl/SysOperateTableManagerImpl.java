package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.ColumnUtils;
import com.sf.shiva.oms.ht.domain.SysOperateTable;
import com.sf.shiva.oms.ht.domain.SysOperateTableExample;
import com.sf.shiva.oms.ht.domain.SysRoleTableInfo;
import com.sf.shiva.oms.ht.manager.SysOperateTableManager;
import com.sf.shiva.oms.ht.mapper.SysOperateTableMapper;
import com.sf.shiva.oms.ht.mapper.extend.SysOperateTableExtMapper;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * OperateTableManager实现类
 *
 * @author 01139932
*/
@Component
public class SysOperateTableManagerImpl implements SysOperateTableManager {

	@Autowired
	private SysOperateTableMapper operateTableMapper;
	
	@Autowired
	private SysOperateTableExtMapper operateTableExtMapper;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return int 记录总数
	 *
    */
	@Override
    public int countByExample(SysOperateTableExample example){
		return operateTableMapper.countByExample(example);
	}

	/**
	 * 按条件删除
	 * 
	 * @param example 条件
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByExample(SysOperateTableExample example){
		return operateTableMapper.deleteByExample(example);
	}
	
	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int deleteByPrimaryKey(Long id){
		return operateTableMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int insert(SysOperateTable record){
		record.setSysTableUid(ColumnUtils.generatorFK());
    	String dateStr = ColumnUtils.nowDateString();
    	record.setCreatetime(dateStr);
    	record.setUpdatetime(dateStr);
    	record.setUpdateuid(UserManager.getCurrentUserNo());
    	record.setDelStatus(false);
	    return operateTableMapper.insert(record);
	}
	
	/**
	 * 新增,null字段不插入
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@Override
    public int insertSelective(SysOperateTable record){
		return operateTableMapper.insertSelective(record);
	}
	
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * 
	 * @return List<OperateTable> 记录列表
	 *
    */
	@Override
    public List<SysOperateTable> selectByExample(SysOperateTableExample example){
		return operateTableMapper.selectByExample(example);
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return OperateTable 记录 
	 *
    */
	@Override
    public SysOperateTable selectByPrimaryKey(Long id){
		return operateTableExtMapper.selectByPrimaryKey(id);
	}

	/**
	 * 按条件更新,null字段不更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
	@Override
    public int updateByExampleSelective(SysOperateTable record, SysOperateTableExample example){
		return operateTableMapper.updateByExampleSelective(record, example);
	}

	
	/**
	 * 按条件更新
	 * 
	 * @param record 记录
	 * @param example 条件
	 * @return int 成功条数  
	 *
    */
	@Override
    public int updateByExample(SysOperateTable record,  SysOperateTableExample example){
		return operateTableMapper.updateByExample(record, example);
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKeySelective(SysOperateTable record){
    	record.setUpdatetime(ColumnUtils.nowDateString());
    	record.setUpdateuid(UserManager.getCurrentUserNo());
		return operateTableExtMapper.updateByPrimaryKeySelective(record);
	}


	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return int 成功条数 
	 *
    */
	@Override
    public int updateByPrimaryKey(SysOperateTable record){
		return operateTableMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysRoleTableInfo> getRoleTableList(String roleId) {
		return operateTableExtMapper.getRoleTableList(roleId);
	}

	@Override
	public int changeUserRole(String tableId, Boolean state, String roleId) {
		int result = 0;
        String dateStr = ColumnUtils.nowDateString();
        if (state) {    // 绑定
        	operateTableExtMapper.unBindRoleTable(tableId, roleId);
            result = operateTableExtMapper.bindRoleTable(dateStr,dateStr,tableId, roleId);
        } else {        // 解除绑定
            result = operateTableExtMapper.unBindRoleTable(tableId, roleId);
        }
        return result;
	}

	@Override
	public int updateToDeleteStatus(Long id) {
		return operateTableExtMapper.updateToDeleteStatus(id);
	}
	
}