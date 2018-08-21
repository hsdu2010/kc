package com.sf.shiva.oms.ht.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.domain.SysOperateTable;
import com.sf.shiva.oms.ht.domain.SysOperateTableExample;
import com.sf.shiva.oms.ht.domain.SysRoleTableInfo;
import com.sf.shiva.oms.ht.manager.SysOperateTableManager;
import com.sf.shiva.oms.ht.service.system.SysOperateTableService;

/**
 * OperateTableService实现类
 *
 * @author 01139932
 */
@Service
public class SysOperateTableServiceImpl implements SysOperateTableService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SysOperateTableManager operateTableManager;

	/**
	 * 按条件计数
	 * 
	 * @param example
	 *            条件
	 * 
	 * @return Integer 记录总数
	 *
	 */
	public Integer countByExample(SysOperateTableExample example) {
		int count = 0;
		try {
			count = operateTableManager.countByExample(example);
		} catch (Exception e) {
			logger.error("countByExample error", e);
		}
		return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return Integer 成功条数
	 *
	 */
	public Integer deleteByPrimaryKey(Long id) {
		if (id == null) {
			throw new IllegalStateException("参数id不能为空");
		}
		int count = 0;
		try {
			count = operateTableManager.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("deleteByPrimaryKey error", e);
		}
		return count;
	}

	/**
	 * 新增
	 * 
	 * @param record
	 *            记录
	 * 
	 * @return Integer 成功条数
	 *
	 */
	public Integer insert(SysOperateTable record) {
		if (record == null) {
			throw new IllegalStateException("参数record不能为空");
		}
		int count = 0;
		try {
			count = operateTableManager.insert(record);
		} catch (Exception e) {
			logger.error("insert error", e);
		}
		return count;
	}

	/**
	 * 按条件查询
	 * 
	 * @param example
	 *            条件
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * 
	 * @return Page
	 *
	 */
	public Page<SysOperateTable> selectByExample(SysOperateTableExample example, Integer pageNum, Integer pageSize) {
		if (pageNum != null && pageNum < 1) {
			throw new IllegalStateException("参数pageNum不能小于1");
		}
		if (pageSize != null && pageSize < 1) {
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if ((pageNum == null && pageSize != null) || (pageNum != null && pageSize == null)) {
			throw new IllegalStateException("pageNum、pageSize必须同时为null或不为null");
		}
		if (pageNum == null && pageSize == null) { // 一次查所有数据
			pageNum = 1;
			pageSize = 0;
		}
		Page<SysOperateTable> records = new Page<SysOperateTable>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<SysOperateTable>) operateTableManager.selectByExample(example);
		} catch (Exception e) {
			logger.error("selectByExample error", e);
		}
		return records;
	}

	/**
	 * 按主键查询
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return List
	 *
	 */
	public SysOperateTable selectByPrimaryKey(Long id) {
		if (id == null) {
			throw new IllegalStateException("参数id不能为空");
		}
		SysOperateTable record = null;
		try {
			record = operateTableManager.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("selectByPrimaryKey error", e);
		}
		return record;
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record
	 *            记录
	 * @return List
	 *
	 */
	public Integer updateByPrimaryKeySelective(SysOperateTable record) {
		if (record == null) {
			throw new IllegalStateException("参数record不能为空");
		}
		if (record.getId() == null) {
			throw new IllegalStateException("主键id属性不能为空");
		}
		int count = 0;
		try {
			count = operateTableManager.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			logger.error("updateByPrimaryKeySelective error", e);
		}
		return count;
	}

	/**
	 * 按主键更新
	 * 
	 * @param record
	 *            记录
	 * @return Integer 成功条数
	 *
	 */
	public Integer updateByPrimaryKey(SysOperateTable record) {
		if (record == null) {
			throw new IllegalStateException("参数record不能为空");
		}
		if (record.getId() == null) {
			throw new IllegalStateException("主键id属性不能为空");
		}
		int count = 0;
		try {
			count = operateTableManager.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

	@Override
	public List<SysRoleTableInfo> getRoleTableList(String roleId) {
		List<SysRoleTableInfo> list = new ArrayList<SysRoleTableInfo>();
		try {
			list = operateTableManager.getRoleTableList(roleId);
		} catch (Exception e) {
			logger.error("getRoleTableList error", e);
		}
		
		return list;
	}

	@Override
	public int changeRoleTable(String tableId, Boolean state, String roleId) {
		int count = 0;
		try {
			count = operateTableManager.changeUserRole(tableId, state, roleId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return count;
	}
	
	@Override
	public Integer updateToDeleteStatus(Long id) {
		return operateTableManager.updateToDeleteStatus(id);
	}

}