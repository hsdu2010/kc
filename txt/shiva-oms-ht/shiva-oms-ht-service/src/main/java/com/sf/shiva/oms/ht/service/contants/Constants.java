package com.sf.shiva.oms.ht.service.contants;



public class Constants {
    
    public static String JAR_HOME = "/home/appdeploy/ht/upload";
    
    public static String REDIS_JAR_HOME = "/home/appdeploy/ht/redis_upload";
    
    /**redis查询，使用哨兵标志*/
    public static final int SENTINEL_FLAG = 1;
    /**redis操作类型，1为查询*/
    public static final int QUERY_REDIS = 1;
    /**redis操作类型，2为删除*/
    public static final int DELETE_REDIS = 2;
}
