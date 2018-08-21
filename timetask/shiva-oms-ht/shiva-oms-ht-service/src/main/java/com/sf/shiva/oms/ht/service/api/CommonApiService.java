package com.sf.shiva.oms.ht.service.api;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.sf.shiva.oms.ht.dto.HttpInvokerDto;
import com.sf.shiva.oms.ht.dto.RpcInvokerDto;
import com.sf.shiva.oms.ht.service.util.BeanUtil;
import com.sf.shiva.oms.ht.service.util.DubboClientUtil;
import com.sf.shiva.oms.ht.service.util.PooledHttpClient;
@Component
public class CommonApiService {
    
    @Autowired
    private PooledHttpClient defaultHttpClient;
   
    public Object invokerRpc(RpcInvokerDto dto) throws Exception {
        Class<?> cls = BeanUtil.loadClass(dto.getInterfaceName(), dto.getJar());
        Method method = BeanUtil.getMethods(dto.getInterfaceName())[Integer.parseInt(dto.getMethodIndex())];
        String [] args = dto.getMethodParam().split("@@");
        Object[] objs = BeanUtil.getMethodArgsValue(method, args);
        Object remote = DubboClientUtil.getInstance(dto.getZkUrl()).getBean(cls, dto.getVersion(), dto.getApplicationName());
        Object res = method.invoke(remote, objs);
        DubboClientUtil.cached(cls.getSimpleName(), remote); 
        return res;
    }
   
    public String invokerRest(HttpInvokerDto dto) throws ParseException, IOException {
        @SuppressWarnings("unchecked")
        Map<String, String> requestHeader = JSON.parse(dto.getRequestHeader(), Map.class);
        return defaultHttpClient.postJsonRequest(dto.getRequest(), dto.getRequestUrl(), requestHeader, dto.getRequestType());
    }
    
    public String invokerWs(String requestBody, String wsUrl) throws IOException{
        Map<String, String> headers = new HashMap<>();
        headers.put("content-Type", "text/xml;charset=utf-8");
        return defaultHttpClient.postJsonRequest(requestBody, wsUrl, headers, true);
    }
}
