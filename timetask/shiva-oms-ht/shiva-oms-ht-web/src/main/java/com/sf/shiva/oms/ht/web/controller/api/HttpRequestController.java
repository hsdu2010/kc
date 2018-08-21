package com.sf.shiva.oms.ht.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.dto.HttpInvokerDto;
import com.sf.shiva.oms.ht.service.api.CommonApiService;

@RequestMapping("interInvoke")
@Controller
public class HttpRequestController {
    
    @Autowired
    private CommonApiService commonApiService;
    
    @RequestMapping("httpRequest")
    @ResponseBody
    public Result<String> httpRequest(HttpInvokerDto dto){
        Result<String> result = new Result<>(true);
        try {
            result.setObj(commonApiService.invokerRest(dto));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }
    
    @RequestMapping("wsRequest")
    @ResponseBody
    public Result<String> wsRequest(String requestBody, String wsUrl){
        Result<String> result = new Result<>(true);
        try {
            result.setObj(commonApiService.invokerWs(requestBody, wsUrl));
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }
}
