package com.sf.shiva.oms.ht.web.controller.system;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.SysOperateTable;
import com.sf.shiva.oms.ht.domain.SysOperateTableExample;
import com.sf.shiva.oms.ht.domain.SysRoleTableInfo;
import com.sf.shiva.oms.ht.service.system.SysOperateTableService;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * OperateTableController类
 *
 * @author 01139932
 */
@RequestMapping("operateTable")
@Controller
public class OperateTableController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysOperateTableService operateTableService;

	/**
	 * 角色拥有的表格
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("getAllTableByRoleId")
	@ResponseBody
	public Result<List<SysRoleTableInfo>> getAllTableByRoleId(String roleId) {
		Result<List<SysRoleTableInfo>> result = new Result<List<SysRoleTableInfo>>();
		result.setSuccess(true);
		result.setObj(operateTableService.getRoleTableList(roleId));
		return result;
	}

	/**
	 * 改变角色是否拥有表格
	 * 
	 * @param tableId
	 * @param state
	 * @param roleId
	 * @return
	 */
	@RequestMapping("changeRoleTable")
	@ResponseBody
	public Result<Boolean> changeRoleTable(String tableId, Boolean state, String roleId) {
		Result<Boolean> result = new Result<Boolean>();
		
		logger.warn(UserManager.getCurrentUserNo() + " change SysOperateTable(whose uid equal " + tableId
				+ ") of SysRoleInfo(whose uid equal " + roleId + ") to " + state);
		
		Boolean boolean1 = operateTableService.changeRoleTable(tableId, state, roleId) > 0;
		if (boolean1) {
			result.setSuccess(true);
			result.setObj(boolean1);
		} else{
			result.setSuccess(false);
		}
			
		return result;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return int 成功条数
	 *
	 */
	@RequestMapping("deleteByPrimaryKey")
	@ResponseBody
	public Result<Boolean> updateToDeleteStatus(Long id) {
		Result<Boolean> result = new Result<Boolean>();

		if (id == null) {
			result.setSuccess(false);
			result.setErrorMessage("主键id属性不能为空");
			return result;
		}
		
		logger.warn(UserManager.getCurrentUserNo() + " disable SysOperateTable(whose id equal " + id + ") ");
		
		int count = 0;
		count = operateTableService.updateToDeleteStatus(id);
		result.setSuccess(true);
		result.setObj(count > 0);
		return result;
	}

	/**
	 * 新增
	 * 
	 * @param record
	 *            记录
	 * 
	 * @return int 成功条数
	 *
	 */
	@RequestMapping("insert")
	@ResponseBody
	public Result<Boolean> insert(SysOperateTable record) {
		Result<Boolean> result = new Result<Boolean>();

		if (record == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数record不能为空");
			return result;
		}
		logger.warn(UserManager.getCurrentUserNo() + " add SysOperateTable  " + record.getSysTableCname());
		int count = 0;
		count = operateTableService.insert(record);
		result.setSuccess(true);
		result.setObj(count > 0);
		return result;
	}

	/**
	 * 按条件查询
	 * 
	 * @param example
	 *            条件
	 * @param offset
	 *            起始条数偏移量
	 * @param pageSize
	 *            每页数量
	 * 
	 * @return List<OperateTable> 记录列表
	 *
	 */
	@RequestMapping("selectByExample")
	@ResponseBody
	public Result<Map<String, Object>> selectByExample(SysOperateTable record, Integer offset, Integer pageSize) {
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();

		if (offset != null && offset < 0) {
			result.setSuccess(false);
			result.setErrorMessage("参数offset不能小于0");
			return result;
		}
		if (pageSize != null && pageSize < 1) {
			result.setSuccess(false);
			result.setErrorMessage("参数pageSize不能小于1");
			return result;
		}
		if ((offset == null && pageSize != null) || (offset != null && pageSize == null)) {
			result.setSuccess(false);
			result.setErrorMessage("offset、pageSize必须同时为null或不为null");
			return result;
		}

		Integer pageNum = null;
		if (pageSize != null) {
			pageNum = (offset.intValue() / pageSize.intValue()) + 1;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysOperateTableExample example = new SysOperateTableExample();
		SysOperateTableExample.Criteria criteria = example.createCriteria();
		criteria.andDelStatusEqualTo(false);
		Page<SysOperateTable> rows = null;
		rows = operateTableService.selectByExample(example, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
		result.setSuccess(true);
		result.setObj(resultMap);
		return result;
	}

	/**
	 * 按主键查询
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return OperateTable 记录
	 *
	 */
	@RequestMapping("selectByPrimaryKey")
	@ResponseBody
	public Result<SysOperateTable> selectByPrimaryKey(Long id) {
		Result<SysOperateTable> result = new Result<SysOperateTable>();

		if (id == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数id不能为空");
			return result;
		}

		result.setSuccess(true);
		result.setObj(operateTableService.selectByPrimaryKey(id));
		return result;
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record
	 *            记录
	 *
	 * @return int 成功条数
	 *
	 */
	@RequestMapping("updateByPrimaryKeySelective")
	@ResponseBody
	public Result<Boolean> updateByPrimaryKeySelective(SysOperateTable record) {
		Result<Boolean> result = new Result<Boolean>();

		if (record == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数record不能为空");
			return result;
		}
		if (record.getId() == null) {
			result.setSuccess(false);
			result.setErrorMessage("主键id属性不能为空");
			return result;
		}
		logger.warn(UserManager.getCurrentUserNo() + " update SysOperateTable  " + record.getSysTableCname());
		
		int count = 0;
		count = operateTableService.updateByPrimaryKeySelective(record);
		result.setSuccess(true);
		result.setObj(count > 0);
		return result;
	}

}