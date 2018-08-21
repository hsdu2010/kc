/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.sf.shiva.oms.ht.service.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;


public class PooledHttpClient implements InitializingBean {

	protected static Logger logger = LoggerFactory.getLogger(PooledHttpClient.class);

	private int socketTimeout;// 从目的主机读取数据超时时间 （配置文件注入）
	private int connectTimeout;// 和目的主机建立连接的超时时间 （配置文件注入）
	private int connectionRequestTimeout;// 从PoolingHttpClientConnectionManager中获取连接超时时间（必填，如果未配置将一直等待从连接池中获取可用连接，此值不易太大）（配置文件注入）
	private static final String CHARSET_UTF8 = "UTF-8";
	private static final int SUCCESS_STATUS = 200;

	private RequestConfig requestConfig;

	@Override
	public void afterPropertiesSet(){
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout)// 设置socket超时时间
				.setConnectTimeout(connectTimeout)// 设置连接超时时间
				.setConnectionRequestTimeout(connectionRequestTimeout)// 设置请求超时时间
				.build();
	}

	/**
	 * 获取连接
	 * 
	 * @return httpclient连接
	 * @author 80002137
	 * @date 2017年5月31日
	 */
	private CloseableHttpClient getConnection() {
		return HttpClients
				.custom()
				.setDefaultRequestConfig(requestConfig)
				.build();
	}

	/**
	 * post请求
	 * 
	 * @param request
	 *            请求
	 * @return result 结果
	 * @author 80002137
	 * @throws IOException 
	 * @date 2017年5月31日
	 */
	public String postJsonRequest(String request, String url, Map<String, String> header, boolean isPost) throws IOException {
		HttpResponse response = null;
		try {
		    RequestBuilder builder = null;
		    if(isPost){
		        builder = RequestBuilder.post();
		    }else{
		        builder = RequestBuilder.get();
		    }
		    builder.setUri(url).setEntity(new StringEntity(request, CHARSET_UTF8));
		    if(header != null){
		       for(String key : header.keySet()){
	              builder.addHeader(key, header.get(key));
	           }
		    }
			response = getConnection().execute(builder.build());
			return EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
		} catch (IOException e) {
			logger.error("http client request failed.", e);
			throw e;
		} finally {
			releaseConnection(response);
		}
	}
	
	/**
	 * post请求
	 * 
	 * @param paramMap
	 *            请求参数
	 * @param url
	 *            请求地址
	 * @return
	 * @author 80002137
	 * @date 2017年8月17日
	 */
	public String postMapRequest(Map<String, String> paramMap, String url) {
		HttpResponse response = null;
		try {
			List<NameValuePair> parameters = new ArrayList<>(paramMap.size());
			paramMap.forEach((key,value) -> parameters.add(new BasicNameValuePair(key, value)));
			response = getConnection().execute(RequestBuilder
												.post()
												.setUri(url)
												.setEntity(new UrlEncodedFormEntity(parameters, CHARSET_UTF8))
												.build());//发送请求
			logger.info("response msg:{}", response); //打印报文
			if (null != response && SUCCESS_STATUS == response.getStatusLine().getStatusCode()) {
				String responseText = EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
				printLogger(paramMap, responseText);
				return responseText;
			}
		} catch (IOException e) {
			logger.error("http client request failed.", e);
		} finally {
			releaseConnection(response);
		}
		return "";
	}
	
	/** 打印请求和接收报文
	 * @param paramMap 请求参数
	 * @param responseText 响应数据
	 * @author 80002517
	 * @date 2017年9月6日
	 */
	private static void printLogger(Map<String, String> paramMap, String responseText){
	    StringBuilder requestStr = new StringBuilder();
	    for(Entry<String, String> entry : paramMap.entrySet()){
	        requestStr.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
	    }
	    logger.info("http request:{}, response info :{}", requestStr, responseText);
	}
	
	/**
	 * 释放连接
	 * 
	 * @param response
	 *            响应
	 * @author 80002137
	 * @date 2017年6月1日
	 */
	private void releaseConnection(HttpResponse response) {
		if (null != response) {
			try {
				EntityUtils.consume(response.getEntity());
			} catch (IOException e) {
				logger.error("EntityUtils.consume(response.getEntity()) error.", e);
			}
		}
	}
	

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

}
