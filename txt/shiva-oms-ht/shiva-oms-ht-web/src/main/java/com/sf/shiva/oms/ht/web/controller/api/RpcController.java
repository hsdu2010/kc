package com.sf.shiva.oms.ht.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.json.JSON;
import com.github.pagehelper.Page;
import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.dto.RpcInvokerDto;
import com.sf.shiva.oms.ht.service.api.CommonApiService;
import com.sf.shiva.oms.ht.service.apimanager.RpcManagerService;

@RequestMapping("rpcMgr")
@Controller
public class RpcController {
    @Autowired
    private CommonApiService commonApiService;
    @Autowired
    private RpcManagerService rpcManagerService;
    
    @RequestMapping(value="invokerRpc", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> invokerRpc(RpcInvokerDto dto) {
        Result<String> result = new Result<>();
        try{
            result.setObj(JSON.json(commonApiService.invokerRpc(dto)));
            result.setSuccess(true);
        }catch(Exception e){
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }
    
    /**
     * 根据应用名模糊查询
     * @param appName
     * @param offset
     * @param pageSize
     * @return
     * @author 01369626
     * @date 2017年12月4日
     */
    @RequestMapping("searchByAppName")
    @ResponseBody
    public Map<String, Object> searchByAppName(String appName, Integer offset, Integer pageSize){
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
		Page<RpcManager> rows = null;
		rows = rpcManagerService.searchByAppName(appName, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
		return resultMap;
    }
}
