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
import com.sf.shiva.oms.ht.domain.ResourceTreeNode;
import com.sf.shiva.oms.ht.domain.SysAreaInfo;
import com.sf.shiva.oms.ht.domain.SysAreaInfoExample;
import com.sf.shiva.oms.ht.service.system.SysAreaInfoService;
import com.sf.shiva.oms.ht.util.UserManager;

/**
 * AreaController类
 *
 * @author 01139932
*/
@RequestMapping("area")
@Controller
public class AreaController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private SysAreaInfoService areaService;
    
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 *
	 * @return int 记录总数
	 *
    */
    @RequestMapping("countByExample")
    @ResponseBody
	public Result<Integer> countByExample(SysAreaInfoExample example){
    	Result<Integer> result = new Result<>();
		int count = 0;
	    count = areaService.countByExample(example);
	    result.setObj(count);
	    return result;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id 主键，自增
	 * 
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public Result<Boolean> updateToDeleteStatus(Long id){
		Result<Boolean> result = new Result<Boolean>();

		if (id == null) {
			result.setSuccess(false);
			result.setErrorMessage("主键id属性不能为空");
			return result;
		}
		
		logger.warn(UserManager.getCurrentUserNo() + " disable SysAreaInfo(whose id equal " + id + ") ");
		
    	int count = 0;
    	count = areaService.updateToDeleteStatus(id);
    	result.setSuccess(true);
		result.setObj(count > 0);
		return result;
    }

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("insert")
    @ResponseBody
    public Result<Boolean> insert(SysAreaInfo record){
		Result<Boolean> result = new Result<Boolean>();

		if (record == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数record不能为空");
			return result;
		}
    	int count = 0;
    	logger.warn(UserManager.getCurrentUserNo() + " add SysAreaInfo  " + record.getSysAreaName());
    	count = areaService.insert(record);
        
    	result.setSuccess(true);
		result.setObj(count > 0);
		return result;
    }
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param offset 起始条数偏移量
	 * @param pageSize  每页数量
	 * 
	 * @return  List<Area> 记录列表 
	 *
    */	
	@RequestMapping("selectByExample")
    @ResponseBody
    public Result<Map<String, Object>> selectByExample(SysAreaInfo record, Integer offset, Integer pageSize){
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
		if(pageSize != null){
			pageNum = (offset.intValue() / pageSize.intValue()) + 1;
		}
   		Map<String, Object> resultMap = new HashMap<String, Object>();
    	SysAreaInfoExample example = new SysAreaInfoExample();
    	SysAreaInfoExample.Criteria criteria = example.createCriteria();
		criteria.andDelStatusEqualTo(false);
    	Page<SysAreaInfo> rows = null;
		rows = areaService.selectByExample(example, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
		result.setSuccess(true);
		result.setObj(resultMap);
		return result;
    }

	/**
	 * 按主键查询
	 * 
	 * @param id 主键，自增
	 * 
	 * @return Area 记录 
	 *
    */	
	@RequestMapping("selectByPrimaryKey")
    @ResponseBody
	public Result<SysAreaInfo> selectByPrimaryKey(Long id){
		Result<SysAreaInfo> result = new Result<SysAreaInfo>();

		if (id == null) {
			result.setSuccess(false);
			result.setErrorMessage("参数id不能为空");
			return result;
		}
	    result.setSuccess(true);
		result.setObj(areaService.selectByPrimaryKey(id));
		return result;
	}
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
     *
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("updateByPrimaryKeySelective")
    @ResponseBody
	public Result<Boolean> updateByPrimaryKeySelective(SysAreaInfo record){
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
		
		logger.warn(UserManager.getCurrentUserNo() + " update SysAreaInfo  " + record.getSysAreaName());
	    int count = 0;
		count = areaService.updateByPrimaryKeySelective(record);
		result.setSuccess(true);
		result.setObj(count > 0);
		return result; 
	}

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
     *
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("updateByPrimaryKey")
    @ResponseBody
    public Result<Boolean> updateByPrimaryKey(SysAreaInfo record){
		Result<Boolean> result = new Result<>();
    	if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
    	int count = 0;
    	count = areaService.updateByPrimaryKey(record);
    	result.setSuccess(count > 0);
        return result;
    }
	
    @RequestMapping("/getResourceList")
    @ResponseBody
    public Result<List<ResourceTreeNode>> getResourceList() {
    	Result<List<ResourceTreeNode>> result = new Result<List<ResourceTreeNode>>();
		result.setSuccess(true);
		result.setObj(areaService.getAreaList());
		return result;
    }
	
}