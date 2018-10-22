package com.sf.shiva.oms.psm.common.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.sf.shiva.oms.psm.common.exception.BusinessException;

import shade.storm.org.apache.http.HttpHost;
import shade.storm.org.apache.http.HttpResponse;
import shade.storm.org.apache.http.client.methods.HttpUriRequest;
import shade.storm.org.apache.http.conn.routing.HttpRoute;
import shade.storm.org.apache.http.entity.StringEntity;
import shade.storm.org.apache.http.util.EntityUtils;
import shade.storm.org.apache.http.client.config.RequestConfig;
import shade.storm.org.apache.http.client.methods.RequestBuilder;
import shade.storm.org.apache.http.impl.client.CloseableHttpClient;
import shade.storm.org.apache.http.impl.client.HttpClients;
import shade.storm.org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * 
 * 描述：HttpClient客户端
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月4日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class PooledHttpClient implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(PooledHttpClient.class);

    private String ip;// ip （配置文件注入）
    private int port;// 端口号 （配置文件注入）
    private int maxRouteConn;// 目标主机的最大连接数 （配置文件注入）
    private int socketTimeout;// 从目的主机读取数据超时时间 （配置文件注入）
    private int connectTimeout;// 和目的主机建立连接的超时时间 （配置文件注入）
    private int connectionRequestTimeout;// 从PoolingHttpClientConnectionManager中获取连接超时时间（必填，如果未配置将一直等待从连接池中获取可用连接，此值不易太大）（配置文件注入）
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final int SUCCESS_STATUS = 200;

    @Autowired
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    private RequestConfig requestConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)// 设置socket超时时间
                .setConnectTimeout(connectTimeout)// 设置连接超时时间
                .setConnectionRequestTimeout(connectionRequestTimeout)// 设置请求超时时间
                .build();
        poolingHttpClientConnectionManager.setMaxPerRoute(new HttpRoute(new HttpHost(ip, port)), maxRouteConn);
    }

    /**
     * 获取连接
     * 
     * @return HttpClient连接
     * @author 568677
     * @date 2017年12月4日
     */
    private CloseableHttpClient getConnection() {
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(poolingHttpClientConnectionManager).build();
    }

    /**
     * 发送请求
     * 
     * @param request
     *            请求
     * @return result 结果
     * @author 80002137
     * @date 2017年5月31日
     */
    public String postRequest(String request, String url) {
        HttpResponse response = null;
        try {
            response = getConnection().execute(createUrlRequest(request, url));
            if (null != response && SUCCESS_STATUS == response.getStatusLine().getStatusCode()) {
                return EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
            }
        } catch (IOException e) {
            logger.error("http client request failed", e);
            throw new BusinessException(e);
        } finally {
            releaseConnection(response);
        }
        return "";
    }

    /**
     * 发送请求
     * 
     * @param request
     *            请求
     * @return result 结果
     * @author 80002137
     * @date 2017年5月31日
     */
    public String getRequest(String url) {
        HttpResponse response = null;
        try {
            response = getConnection().execute(createUrlGetRequest(url));
            if (null != response && SUCCESS_STATUS == response.getStatusLine().getStatusCode()) {
                return EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
            }
        } catch (IOException e) {
            logger.error("http client request failed", e);
        } finally {
            releaseConnection(response);
        }
        return "";
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
                logger.error("EntityUtils.consume(response.getEntity()) error", e);
            }
        }
    }

    /**
     * 创建post连接
     * 
     * @param request
     *            请求json
     * @param url
     *            请求url
     * @return 请求对象
     * @author 80002137
     * @date 2017年5月31日
     */
    private HttpUriRequest createUrlRequest(String request, String url) {
        return RequestBuilder.post().setUri(url).setEntity(new StringEntity(request, CHARSET_UTF8)).addHeader(CONTENT_TYPE, APPLICATION_JSON).build();
    }

    /**
     * 创建get连接
     * 
     * @param url
     *            请求url
     * @return
     * @author 01159741
     * @date 2017年8月25日
     */
    private HttpUriRequest createUrlGetRequest(String url) {
        return RequestBuilder.get().setUri(url).setEntity(new StringEntity("", CHARSET_UTF8)).addHeader(CONTENT_TYPE, APPLICATION_JSON).build();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxRouteConn() {
        return maxRouteConn;
    }

    public void setMaxRouteConn(int maxRouteConn) {
        this.maxRouteConn = maxRouteConn;
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
