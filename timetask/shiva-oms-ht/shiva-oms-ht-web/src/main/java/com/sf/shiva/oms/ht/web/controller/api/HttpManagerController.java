package com.sf.shiva.oms.ht.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.HttpManager;
import com.sf.shiva.oms.ht.domain.HttpManagerExample;
import com.sf.shiva.oms.ht.service.apimanager.HttpManagerService;

/**
 * HttpManagerController类
 *
 * @author 01369626
*/
@RequestMapping("httpMgr")
@Controller
public class HttpManagerController {

	@Autowired
    private HttpManagerService userHttpInfoService;
    
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
	public Integer countByExample(HttpManagerExample example){
		int count = 0;
	    count = userHttpInfoService.countByExample(example);
	    return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id 
	 * 
	 * @return int 成功条数  
	 *
    */
	@SuppressWarnings("rawtypes")
    @RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public Result deleteByPrimaryKey(String id){
	    Result result= new Result<>();
	    result.setSuccess(true);
	    try{
	        int count = 0;
	        count = userHttpInfoService.deleteByPrimaryKey(id);
	        if(count  < 1){
	            result.setSuccess(false);
	        }
	    }catch(Exception e){
	        result.setSuccess(false);
	        result.setErrorMessage(e.getMessage());
	    }
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
	@SuppressWarnings("rawtypes")
    @RequestMapping(value="insert", method=RequestMethod.POST)
    @ResponseBody
    public Result insert(HttpManager record){
	    Result result = new Result<>();
	    result.setSuccess(false);
    	if(record == null){
    	    result.setErrorMessage("参数record不能为空");
		}
    	int count = 0;
    	count = userHttpInfoService.insert(record);
        if(count > 0){
            result.setSuccess(true);
        }
        return result;
    }
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param offset 起始条数偏移量
	 * @param pageSize  每页数量
	 * 
	 * @return  List<HttpManager> 记录列表 
	 *
    */	
	@RequestMapping("selectByExample")
    @ResponseBody
    public Map<String, Object> selectByExample(HttpManager record, Integer offset, Integer pageSize){
    	if(offset != null && offset < 0){
			throw new IllegalStateException("参数offset不能小于0");
		}
		if(pageSize != null && pageSize < 1){
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if((offset == null && pageSize != null)
			||(offset != null && pageSize == null)){
			throw new IllegalStateException("offset、pageSize必须同时为null或不为null");
		}
		Integer pageNum = null;
		if(pageSize != null){
			pageNum = (offset.intValue() / pageSize.intValue()) + 1;
		}
   		Map<String, Object> resultMap = new HashMap<>();
    	HttpManagerExample example = new HttpManagerExample(); 
    	Page<HttpManager> rows = null;
		rows = userHttpInfoService.selectByExample(example, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
        return resultMap;
    }

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return HttpManager 记录 
	 *
    */	
	@RequestMapping("selectByPrimaryKey")
    @ResponseBody
	public HttpManager selectByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
	    return userHttpInfoService.selectByPrimaryKey(id);
	}
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
     *
	 * @return int 成功条数  
	 *
    */
	@SuppressWarnings("rawtypes")
    @RequestMapping("updateByPrimaryKeySelective")
    @ResponseBody
	public Result updateByPrimaryKeySelective(HttpManager record){
	    Result result = new Result();
	    result.setSuccess(false);
		if(record == null){
		    result.setErrorMessage("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    	    result.setErrorMessage("主键id属性不能为空");
    	}
    	try{
    	    int count = 0;
            count = userHttpInfoService.updateByPrimaryKeySelective(record);
            if(count > 0){
                result.setSuccess(true);
            }
    	}catch(Exception e){
    	    result.setErrorMessage(e.getMessage());
    	}
	   return result;
	}
	
	@RequestMapping("searchByRemark")
	@ResponseBody
	public Map<String, Object> searchByRemark(String remark, Integer offset, Integer pageSize){
		if(offset != null && offset < 0){
			throw new IllegalStateException("参数offset不能小于0");
		}
		if(pageSize != null && pageSize < 1){
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if((offset == null && pageSize != null)
			||(offset != null && pageSize == null)){
			throw new IllegalStateException("offset、pageSize必须同时为null或不为null");
		}
		Integer pageNum = null;
		if(pageSize != null){
			pageNum = (offset.intValue() / pageSize.intValue()) + 1;
		}
		Map<String, Object> resultMap = new HashMap<>();
		Page<HttpManager> rows = null;
		rows = userHttpInfoService.searchByRemark(remark, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
		return resultMap;
	}
}