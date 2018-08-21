package com.sf.shiva.oms.ht.web.controller.system;

import java.util.Map;
import java.util.ArrayList;
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
import com.sf.shiva.oms.ht.domain.ResourceTreeNode;
import com.sf.shiva.oms.ht.domain.SysResourceInfo;
import com.sf.shiva.oms.ht.domain.SysResourceInfoExample;
import com.sf.shiva.oms.ht.service.system.SysResourceInfoService;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * ResourceController类
 *
 * @author 01139932
 */
@RequestMapping("resource")
@Controller
public class ResourceController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysResourceInfoService resourceService;

	/**
	 * 更改用户的资源列
	 * 
	 * @param roleId
	 * @param resList
	 * @return
	 */
	@RequestMapping("updateRoleResource")
	@ResponseBody
	public Result<Boolean> updateRoleResource(String roleId, String resList) {

		logger.warn(
				UserManager.getCurrentUserNo() + " update SysResourceInfo(whose uid equal " + roleId + ")   resourceList");

		Result<Boolean> result = new Result<Boolean>();
		List<String> resourceIdList = new ArrayList<String>();

		String[] resStrList = resList.split(",");
		for (String string : resStrList) {
			resourceIdList.add(string);
		}

		resourceService.updateRoleResource(roleId, resourceIdList);
		result.setSuccess(Boolean.TRUE);
		return result;
	}

	/**
	 * 获取用户的资源，并封装
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("getRoleResource")
	@ResponseBody
	public Result<List<ResourceTreeNode>> getRoleResource(String roleId) {
		Result<List<ResourceTreeNode>> result = new Result<List<ResourceTreeNode>>();
		result.setSuccess(true);
		result.setObj(resourceService.getResourceByRoleId(roleId));
		return result;
	}

	/**
	 * 获取所有资源 封装
	 * 
	 * @return
	 */
	@RequestMapping("getResourceList")
	@ResponseBody
	public Result<List<ResourceTreeNode>> getResourceList() {
		Result<List<ResourceTreeNode>> result = new Result<List<ResourceTreeNode>>();
		result.setSuccess(true);
		result.setObj(resourceService.getResourceList());
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
		
		logger.warn(UserManager.getCurrentUserNo() + " disable SysResourceInfo(whose id equal " + id + ") ");
		
		int count = 0;
		count = resourceService.updateToDeleteStatus(id);
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
	public Result<Boolean> insert(SysResourceInfo record) {
		Result<Boolean> result = new Result<Boolean>();

		if (record == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数record不能为空");
			return result;
		}
		logger.warn(UserManager.getCurrentUserNo() + " add SysResourceInfo  " + record.getSysResourceName());
		int count = 0;
		count = resourceService.insert(record);

		result.setSuccess(true);;
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
	 * @return List<Resource> 记录列表
	 *
	 */
	@RequestMapping("selectByExample")
	@ResponseBody
	public Result<Map<String, Object>> selectByExample(SysResourceInfo record, Integer offset, Integer pageSize) {
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
		SysResourceInfoExample example = new SysResourceInfoExample();
		SysResourceInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelStatusEqualTo(false);
		Page<SysResourceInfo> rows = null;
		rows = resourceService.selectByExample(example, pageNum, pageSize);
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
	 * @return Resource 记录
	 *
	 */
	@RequestMapping("selectByPrimaryKey")
	@ResponseBody
	public Result<SysResourceInfo> selectByPrimaryKey(Long id) {
		Result<SysResourceInfo> result = new Result<SysResourceInfo>();

		if (id == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数id不能为空");
			return result;
		}
		result.setSuccess(true);
		result.setObj(resourceService.selectByPrimaryKey(id));
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
	public Result<Boolean> updateByPrimaryKeySelective(SysResourceInfo record) {
		Result<Boolean> result = new Result<Boolean>();

		if (record == null) {
			result.setSuccess(false);;
			result.setErrorMessage("参数record不能为空");
			return result;
		}
		if (record.getId() == null) {
			result.setSuccess(false);;
			result.setErrorMessage("主键id属性不能为空");
			return result;
		}
		
		logger.warn(UserManager.getCurrentUserNo() + " update SysResourceInfo  " + record.getSysResourceName());
		
		int count = 0;
		count = resourceService.updateByPrimaryKeySelective(record);
		result.setSuccess(true);
		result.setObj(count > 0);
		return result;
	}

}