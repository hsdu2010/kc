package com.sf.shiva.oms.ht.web.controller.system;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.SysUserInfo;
import com.sf.shiva.oms.ht.domain.SysUserInfoExample;
import com.sf.shiva.oms.ht.service.system.SysUserInfoService;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * UserController类
 *
 * @author 01139932
 */
@RequestMapping("user")
@Controller
public class UserController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysUserInfoService userService;

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
	 * @return List<User> 记录列表
	 *
	 */
	@RequestMapping("selectByExample")
	@ResponseBody
	public Result<Map<String, Object>> selectByExample(SysUserInfo record, Integer offset, Integer pageSize) {
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
		SysUserInfoExample example = new SysUserInfoExample();
		SysUserInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelStatusEqualTo(false);
		Page<SysUserInfo> rows = null;
		rows = userService.selectByExample(example, pageNum, pageSize);
		
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
		result.setSuccess(true);
		result.setObj(resultMap);
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
	public Result<Boolean> insert(SysUserInfo record) {
		
		Result<Boolean> result = new Result<Boolean>();
		
		if (record == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数record不能为空");
			return result;
		}
		
		logger.warn(UserManager.getCurrentUserNo() + " add  SysUserInfo  " + record.getSysUsername());
		
		int count = 0;
		count = userService.insert(record);
		
		result.setSuccess(true);
		result.setObj(count > 0);
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
	public Result<Boolean> updateByPrimaryKeySelective(SysUserInfo record) {
		Result<Boolean> result = new Result<Boolean>();
		
		if (record == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数record不能为空");
			return result;
		}
		logger.warn(UserManager.getCurrentUserNo() + " update SysUserInfo  " + record.getSysUsername());
		
		if (record.getId() == null) {
			result.setSuccess(false);
			result.setErrorMessage("主键id属性不能为空");
			return result;
		}
		int count = 0;
		count = userService.updateByPrimaryKeySelective(record);
		
		result.setSuccess(true);
		result.setObj(count > 0);
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
			result.setSuccess(true);
			result.setErrorMessage("主键id属性不能为空");
			return result;
		}		
		logger.warn(UserManager.getCurrentUserNo() + " disable SysUserInfo(whose id equal " + id+")");
		int count = 0;
		count = userService.updateToDeleteStatus(id);
		
		result.setSuccess(true);
		result.setObj(count > 0);
		return result;
	}

	/**
	 * 按主键查询
	 * 
	 * @param id
	 *            主键，自增
	 * 
	 * @return User 记录
	 *
	 */
	@RequestMapping("selectByPrimaryKey")
	@ResponseBody
	public Result<SysUserInfo> selectByPrimaryKey(Long id) {
		Result<SysUserInfo> result = new Result<SysUserInfo>();
		
		if (id == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数id不能为空");
			return result;
		}
		
		result.setSuccess(true);
		result.setObj(userService.selectByPrimaryKey(id));
		return result;
	}

	/**
	 * 修改密码
	 * @param id
	 * @param orignPsd
	 * @param newPsd
	 * @param newPsdConfirm
	 * @return
	 */
	@RequestMapping("updatePsdByPrimaryKeySelective")
	@ResponseBody
	public Result<Boolean> updatePsdByPrimaryKeySelective(Long id,String orignPsd,String newPsd,String newPsdConfirm){
		Result<Boolean> result = new Result<Boolean>();
		
		logger.warn(UserManager.getCurrentUserNo() + " update SysUserInfo(whose id equal " + id+") password");
		
		if (id == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数id不能为空");
			return result;
		}
		
		if (!newPsd.equals(newPsdConfirm)) {
			result.setSuccess(false);
			result.setErrorMessage("两次密码不一致");
			return result;
		}
		
		if (newPsd.equals(orignPsd)) {
			result.setSuccess(false);
			result.setErrorMessage("新旧密码一致");
			return result;
		}
		
		SysUserInfo userInfo  = userService.selectByPrimaryKey(id);
		
		if (userInfo == null) {
			result.setSuccess(false);
			result.setErrorMessage("用户ID不存在");
			return result;
		}	
		
		if (!orignPsd.equals(userInfo.getSysPwd())) {
			result.setSuccess(false);
			result.setErrorMessage("原始密码错误");
			return result;
		}
		
		int count = 0;
		userInfo.setSysPwd(newPsd);
		count = userService.updateByPrimaryKeySelective(userInfo);		
		if (count>0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
			result.setErrorMessage("修改失败");
		}
		
		return result;
	}
}