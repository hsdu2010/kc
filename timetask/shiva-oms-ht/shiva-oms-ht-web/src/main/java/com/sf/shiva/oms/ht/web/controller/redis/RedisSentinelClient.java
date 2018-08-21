package com.sf.shiva.oms.ht.web.controller.redis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;

/**
 * 处理缓存的公共类 提供存入缓存的公共方法以下静态方法
 *
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1  2016/05/13      80002071         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002071 Zhu Hongwei
 */
public class RedisSentinelClient {

    private static final Logger LOG = LoggerFactory.getLogger(RedisSentinelClient.class);

    private static final long DEFAULT_MAX_WAIT_MILLS = 2000L;// 默认的等待时间
    private static final int TIME_OUT = 60000;// 超时时间

    private static ShardedJedisSentinelPool shardedJedisSentinelPool;// Redis多实例池

    private static boolean isCompleteInit = false;// 是否完成初始化

    private String password="admin.123";// Redis服务密码 --
    private int maxActive=10;// 连接池中最大连接数
    private int maxIdle=5;// 连接池中最大空闲的连接数
    private int minIdle=2;// 连接池中最小空闲的连接数
    private Long softMinEvictableIdleTimeMillis=10L;// 连接空闲的最小时间,达到此值后空闲链接将会被移除,且保留空闲连接数
    private String redis1="mymaster1";// 主缓存，多个用逗号分隔 --
    private String sentinel = "10.202.11.89:8001";// 监控哨兵的IP和端口,多个用逗号隔开 --
    private long maxWaitMillis = DEFAULT_MAX_WAIT_MILLS;// 最大等待时间，毫秒，默认2000毫秒

    private static RedisSentinelClient INSTANCE;

    /**
     * 初始化监控器，初始化主机，初始化连接池，获得一个对象序列化的实例
     */
    public void initSentinelPool() {
        Set<String> sentinels = initSentinel();// 初始化监控机器
        List<String> masters = initMasters();// 初始化主机
        GenericObjectPoolConfig config = initPool();// 初始化连接池
        if (password == null || password.trim().equals("")) {
            shardedJedisSentinelPool = new ShardedJedisSentinelPool(masters, sentinels, config,
                    TIME_OUT);
        } else {
            shardedJedisSentinelPool = new ShardedJedisSentinelPool(masters, sentinels, config,
                    TIME_OUT, password);
        }
        INSTANCE = this;
        isCompleteInit = true;
//        utilObjectToBytes = new ProtostuffSerializeImpl();// 获得一个对象实例化的实例
    }

    /**
     * 初始化连接池
     * 
     * @return 设置连接池中的相关参数
     */
    private GenericObjectPoolConfig initPool() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }

    /**
     * 初始化监听器
     * 
     * @return 监听器中的相关参数
     */
    private Set<String> initSentinel() {
        String sentinelStr = sentinel;// 监控IP和端口
        Set<String> sentinels = new HashSet<String>();
        String[] sentinelArray = sentinelStr.split(",");
        for (int i = 0; i < sentinelArray.length; i++) {
            sentinels.add(sentinelArray[i]);
        }
        return sentinels;
    }

    /**
     * 初始化主机
     * 
     * @return 主机中的相关参数
     */
    private List<String> initMasters() {
        List<String> masters = new ArrayList<String>();
        String[] masterName = redis1.split(",");
        for (int i = 0; i < masterName.length; i++) {
            masters.add(masterName[i]);// 监控主机名
        }
        return masters;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public void setSoftMinEvictableIdleTimeMillis(Long softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    public void setRedis1(String redis1) {
        this.redis1 = redis1;
    }

    public void setSentinel(String sentinel) {
        this.sentinel = sentinel;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    /**
     * 从REDIS池中获取SharedJedis
     * 
     * @return SharedJedis
     */
    public static ShardedJedis getSharedJedis() {
        return shardedJedisSentinelPool.getResource();
    }

    /**
     * 回收资源
     * 
     * @param shardedJedis
     *            SharedJedis
     */
    @SuppressWarnings("deprecation")
	public static void recycleResources(ShardedJedis shardedJedis) {
        if (null != shardedJedis) {
            try {
                shardedJedisSentinelPool.returnResource(shardedJedis);
            } catch (Exception e) {
                LOG.error("Return ShardedJedis to pool error.", e);
            }
        }
    }

    /**
     * 在异常时回收资源
     * 
     * @param shardedJedis
     *            SharedJedis
     */
    @SuppressWarnings("deprecation")
	public static void recycleBrokenResources(ShardedJedis shardedJedis) {
        if (null != shardedJedis) {
            try {
                shardedJedisSentinelPool.returnBrokenResource(shardedJedis);
            } catch (Exception e) {
                LOG.error("Return ShardedJedis to pool error.", e);
            }
        }
    }

    public static RedisSentinelClient getInstance() {
        return INSTANCE;
    }

    /**
     * 是否完成初始化
     * 
     * @return true：完成初始化；false：没有完成
     * @author 866321-2016年12月15日
     */
    public static boolean isCompleteInit() {
        return isCompleteInit;
    }

}
