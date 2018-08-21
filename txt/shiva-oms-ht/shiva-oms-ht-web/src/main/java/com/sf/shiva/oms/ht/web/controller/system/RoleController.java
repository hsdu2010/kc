package com.sf.shiva.oms.ht.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.SysRoleInfo;
import com.sf.shiva.oms.ht.domain.SysRoleInfoExample;
import com.sf.shiva.oms.ht.domain.SysUserRoleInfo;
import com.sf.shiva.oms.ht.service.system.SysRoleInfoService;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * RoleController类
 *
 * @author 01139932
 */
@RequestMapping("role")
@Controller
public class RoleController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysRoleInfoService roleService;

	/**
	 * 获取用户的角色
	 * 
	 * @param userUid
	 * @return
	 */
	@RequestMapping("getAllRoleByUserNo")
	@ResponseBody
	public Result<List<SysUserRoleInfo>> getAllRoleByUserNo(String userUid) {
		Result<List<SysUserRoleInfo>> result = new Result<List<SysUserRoleInfo>>();

		List<SysUserRoleInfo> list = roleService.getUserRoleList(userUid);

		result.setSuccess(true);
		result.setObj(list);
		return result;
	}

	/**
	 * 改变用户是否绑定角色
	 * 
	 * @param roleUid
	 * @param state
	 * @param userUid
	 * @return
	 */
	@RequestMapping("changeUserRole")
	@ResponseBody
	public Result<Boolean> changeUserRole(String roleUid, Boolean state, String userUid) {
		Result<Boolean> result = new Result<Boolean>();

		logger.warn(UserManager.getCurrentUserNo() + " change SysRoleInfo(whose uid equal " + roleUid
				+ ") of SysUserInfo(whose uid equal " + userUid + ") to " + state);

		int count = roleService.changeUserRole(roleUid, state, userUid);
		if (count > 0) {
			result.setSuccess(true);
			result.setObj(count > 0);
		} else {
			result.setSuccess(false);
			result.setErrorMessage("改变绑定状态失败");
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

		logger.warn(UserManager.getCurrentUserNo() + " disable SysRoleInfo(whose id equal " + id + ") ");
		
		int count = 0;
		count = roleService.updateToDeleteStatus(id);

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
	public Result<Boolean> insert(SysRoleInfo record) {
		Result<Boolean> result = new Result<Boolean>();

		if (record == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数record不能为空");
			return result;
		}
		
		logger.warn(UserManager.getCurrentUserNo() + " add SysRoleInfo  " + record.getSysRoleName());
		int count = 0;
		count = roleService.insert(record);

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
	 * @return List<Role> 记录列表
	 *
	 */
	@RequestMapping("selectByExample")
	@ResponseBody
	public Result<Map<String, Object>> selectByExample(SysRoleInfo record, Integer offset, Integer pageSize) {
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
			result.setSuccess(false);;
			result.setErrorMessage("offset、pageSize必须同时为null或不为null");
			return result;
		}

		Integer pageNum = null;
		if (pageSize != null) {
			pageNum = (offset.intValue() / pageSize.intValue()) + 1;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysRoleInfoExample example = new SysRoleInfoExample();
		SysRoleInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelStatusEqualTo(false);
		Page<SysRoleInfo> rows = null;
		rows = roleService.selectByExample(example, pageNum, pageSize);
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
	 * @return Role 记录
	 *
	 */
	@RequestMapping("selectByPrimaryKey")
	@ResponseBody
	public Result<SysRoleInfo> selectByPrimaryKey(Long id) {
		Result<SysRoleInfo> result = new Result<SysRoleInfo>();

		if (id == null) {
			result.setSuccess(false);;
			result.setErrorMessage("参数id不能为空");
			return result;
		}

		result.setSuccess(true);
		result.setObj(roleService.selectByPrimaryKey(id));
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
	public Result<Boolean> updateByPrimaryKeySelective(SysRoleInfo record) {
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
		
		logger.warn(UserManager.getCurrentUserNo() + " update SysRoleInfo  " + record.getSysRoleName());
		
		int count = 0;
		count = roleService.updateByPrimaryKeySelective(record);

		result.setSuccess(true);
		result.setObj(count > 0);
		return result;
	}

}