package com.sf.shiva.oms.ht.service.kafka.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;



/**
 * 描述：请求kafka管理系统网关
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月28日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
@SuppressWarnings({ "resource", "deprecation" })
@Component
public class KafkaMommonGateWayService implements InitializingBean{
    
    private Logger logger = LoggerFactory.getLogger(KafkaMommonGateWayService.class);
    public Map<String, String> CLUSTERMAP = new HashMap<>(); //集群信息
    private static final String QUERY_TOPIC_URL =
            "http://mommon.sit.sf-express.com/mom-mon/monitor/queryTopic.arz";
    private static final String QUERY_CLUSTER_URL = "http://mommon.sit.sf-express.com/mom-mon/monitor/queryCluster.arz";
    
    static{
        MommonLogin.login();
    }
    
    private static List<NameValuePair> mapToNameValuePair(Map<String, String> param){
        List<NameValuePair> parmasList = new ArrayList<>(); 
        if(MapUtils.isNotEmpty(param)){
            for(String key : param.keySet()){
                parmasList.add(new BasicNameValuePair(key, param.get(key)));
            }
        }
        return parmasList;
    }
    
    private static void addRequestHeader(HttpUriRequest request){
        request.addHeader("Cookie", "JSESSIONID=" + MommonLogin.jessionid);
    }
    
    public static String requestPost(String url, Map<String, String> param){
        try {
            HttpPost request = new HttpPost(url);
            addRequestHeader(request);
            request.setEntity(new UrlEncodedFormEntity(mapToNameValuePair(param)));
            HttpResponse response = new DefaultHttpClient().execute(request);
            if(response.getStatusLine().getStatusCode() == 302){
                MommonLogin.login();
                return requestPost(url, param);
            }
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        //获取集群环境
        String responseText = null;
        try{
            responseText = retryPost(QUERY_CLUSTER_URL, null);
            JSONArray arrays = JSONArray.fromObject(responseText);
            for(Object obj : arrays){
                JSONObject jsonObject = (JSONObject) obj;
                CLUSTERMAP.put("sit-"+jsonObject.getString("text"), jsonObject.getString("id"));
            }
        }catch(Exception e){
            logger.error("queryCluster error response:{}", responseText, e);
        }
    }
    
    /**
     * 重试Post
     * **/
    private static String retryPost(String url, Map<String, String> param){
        int retryNum = 2;
        String responseMsg;
        while(retryNum > 0){
            responseMsg = requestPost(url, param);
            if(StringUtils.isNoneEmpty(responseMsg)){
                return responseMsg;
            }
            retryNum --;
        }
        return "";
    }
    
    private static Map<String, String> buildFirstRecordReq(){
        Map<String, String> param = new HashMap<>();
        param.put("page", "1");
        param.put("limit", "1");
        param.put("start", "0");
        return param;
    }
    
    /** 
     * 获取主题信息
     * @param topic 主题名
     * @param clusterName 集群名
     * @return 主题信息
     * @author 80002517
     * @date 2018年5月31日
     */
    public  String getTopicInfo(String topic, String clusterName){
        String responseText = null;
        try{
            Map<String, String> req = buildFirstRecordReq();
            req.put("topicName", topic);
            req.put("clusterId", CLUSTERMAP.get(clusterName));
            responseText = retryPost(QUERY_TOPIC_URL, req);
            return responseText;
        }catch(Exception e){
            logger.error("queryTopicInfo error response:{}",responseText, e);
            return null;
        }
    }
    
    /** 
     * 获取分区数
     * @param topic 主题
     * @param clusterName 集群名
     * @return 分区数
     * @author 80002517
     * @date 2018年5月31日
     */
    public int getTotalParitionNum(String topic, String clusterName) {
        try{
            String responseMsg = this.getTopicInfo(topic, "sit-"+clusterName);
            JSONObject obj = JSONObject.fromObject(responseMsg).getJSONArray("rows").getJSONObject(0);
            if(obj != null){
                return obj.getInt("partitionCount");
            }
        }catch(Exception e){
        }
        return 4;
    }

    static class MommonLogin{
        
        private static String jessionid; //登录成功后的jsessionid
        private static final String INDEX = "http://mommon.sit.sf-express.com/mom-mon/system.pvt"; //登录请求地址
        private static final String LOGINURL = "http://cas.sit.sf-express.com/cas/login";
        /** 
         * 登录moom
         * @author 80002517
         * @date 2017年11月28日
         */
        public static void login(){
            try {
                HttpGet get = new HttpGet(INDEX);  
                HttpResponse response = new DefaultHttpClient().execute(get);
                HttpPost post = new HttpPost(LOGINURL);  
                post.addHeader("Cookie", "selCty=0;"+response.getFirstHeader("Set-Cookie").getValue().split(";")[0]+"; "+response.getLastHeader("Set-Cookie").getValue().split(";")[0]);
                post.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false); 
                post.setEntity(new UrlEncodedFormEntity(mapToNameValuePair(getLoginReqParam(getHiddenParam(response.getEntity())))));  
                response = new DefaultHttpClient().execute(post);
                String referer2 = response.getFirstHeader("Location").getValue();
                get = new HttpGet(referer2);
                CookieStore cookieStore = new BasicCookieStore();
               HttpClients.custom().setDefaultCookieStore(cookieStore).build().execute(get);
                List<Cookie> cookies = cookieStore.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    if (cookies.get(i).getName().equals("JSESSIONID")) {
                        jessionid = cookies.get(i).getValue();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        /** 
         * 获取lt
         * @param httpEntity
         * @return
         * @throws ParseException
         * @throws IOException
         * @author 80002517
         * @date 2017年11月28日
         */
        private static String getHiddenParam(HttpEntity httpEntity) throws ParseException, IOException{
            String text = EntityUtils.toString(httpEntity, "UTF-8");
            int endIndex = text.indexOf("<input type=\"hidden\" name=\"_eventId\"");
            return text.substring(endIndex - 50, endIndex-6);
        }
        
        /** 
         * 登录请求参数
         * @param lt
         * @return
         * @author 80002517
         * @date 2017年11月28日
         */
        private static Map<String, String> getLoginReqParam(String lt){
            Map<String, String> param = new HashMap<>();
            param.put("_eventId", "submit");
            param.put("username", "089245");
            param.put("password", "1212");
            param.put("lt", lt);
            return param;
        }
    }
}
