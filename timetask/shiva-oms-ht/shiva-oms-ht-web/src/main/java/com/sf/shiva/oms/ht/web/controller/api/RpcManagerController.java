package com.sf.shiva.oms.ht.web.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.domain.RpcManagerExample;
import com.sf.shiva.oms.ht.dto.ClassInfoDto;
import com.sf.shiva.oms.ht.exception.BusinessException;
import com.sf.shiva.oms.ht.service.apimanager.RpcManagerService;

/**
 * UserRpcInfoController类
 *
 * @author 01369626
*/
@RequestMapping("rpcMgr")
@Controller
public class RpcManagerController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
    private RpcManagerService rpcManagerService;
    
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
	public Integer countByExample(RpcManagerExample example){
		int count = 0;
	    count = rpcManagerService.countByExample(example);
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
	    Result result = new Result<>(false);
	    try{
	        if(id == null || "".equals(id.trim())){
	            result.setErrorMessage("参数id不能为空");
	        }
	        int count = 0;
	        count = rpcManagerService.deleteByPrimaryKey(id);
	        if(count > 0){
	            result.setSuccess(true);
	        }
	    }catch(Exception e){
	        result.setErrorMessage(e.getMessage());
	    }
	    return result;
    }

	@RequestMapping(value="insert", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> insert(@RequestParam("uploadjar") MultipartFile file, RpcManager record) {
        Result<Boolean> result = new Result<>();
        try{
            if(file.getSize() == 0){
                result.setSuccess(false);
                result.setErrorMessage("文件内容不能为空");
            }else{
                rpcManagerService.insert(record, file.getInputStream());
            }
            
            result.setSuccess(true);
        }catch(BusinessException e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }catch(Exception e){
            logger.error("insert RpcInfo systemError", e);
            result.setSuccess(false);
            result.setErrorMessage("系统错误");
        }
        return result;
    }
	
	
	@RequestMapping("queryInterfaceInfo")
    @ResponseBody
    public Result<List<ClassInfoDto>> queryInterfaceInfo(String interfaceName, String jarName) {
        Result<List<ClassInfoDto>> result = new Result<>();
        try{
            result.setObj(rpcManagerService.queryJarInfo(jarName));
            result.setSuccess(true);
        }catch(Exception e){
            logger.error("insert RpcInfo systemError", e);
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
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
	 * @return  List<UserRpcInfo> 记录列表 
	 *
    */	
	@RequestMapping("selectByExample")
    @ResponseBody
    public Result<Map<String, Object>> selectByExample(RpcManager record, Integer offset, Integer pageSize){
	    Result<Map<String, Object>> result = new Result<Map<String, Object>>();
    	if(offset != null && offset < 0){
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
    	RpcManagerExample example = new RpcManagerExample(); 
    	Page<RpcManager> rows = null;
		rows = rpcManagerService.selectByExample(example, pageNum, pageSize);
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
	 * 
	 * @return UserRpcInfo 记录 
	 *
    */	
	@RequestMapping("selectByPrimaryKey")
    @ResponseBody
	public RpcManager selectByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
	    return rpcManagerService.selectByPrimaryKey(id);
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
    public Boolean updateByPrimaryKeySelective(RpcManager record){
        if(record == null){
            throw new IllegalStateException("参数record不能为空");
        }
        if(record.getId() == null || "".equals(record.getId().trim())){
            throw new IllegalStateException("主键id属性不能为空");
        }
        int count = 0;
        count = rpcManagerService.updateByPrimaryKeySelective(record);
        return count > 0; 
    }
	
}