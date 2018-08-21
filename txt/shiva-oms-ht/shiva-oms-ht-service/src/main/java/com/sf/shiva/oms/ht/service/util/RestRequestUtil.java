package com.sf.shiva.oms.ht.service.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * rest请求工具类
 * @author 80002031
 *
 */
public class RestRequestUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(RestRequestUtil.class);
	
	private static CloseableHttpClient httpClient = HttpClients.createDefault() ;
		
	private static class InstanceHolder {    
	   private static final RestRequestUtil INSTANCE = new RestRequestUtil();
	}
	
    private RestRequestUtil (){}    
    
    public static final RestRequestUtil getInstance() { 
       return InstanceHolder.INSTANCE;    
    }    
    
    /**
     * rest接口请求
     * @param json	报文信息
     * @param url	请求路径
     * @return	返回结果
     */
    public String request(String json,String url) { 
    	if (null != httpClient) {
    		HttpPost httpPost = new HttpPost(url);
    		httpPost.setHeader("Content-Type", "application/json");
    		StringEntity entity = null;
    		entity = new StringEntity(json, "UTF-8");
    		httpPost.setEntity(entity);
    		HttpResponse response;
			try {
				response = httpClient.execute(httpPost);
				HttpEntity respEntity = response.getEntity();
				return EntityUtils.toString(respEntity, "UTF-8");
			} catch (IOException e) {
				logger.error(" rest request execute error ! json : {} , url :{} " ,json , url );
			}
    	}
		return "";
    }
    
    
}
