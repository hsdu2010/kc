package com.sf.shiva.oms.ht.service.redis.utils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sf.kafka.check.util.JsonUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 
 * 描述：redis操作类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月3日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class RedisOperator {
	private JedisPool jedisPool;
	private JedisPoolConfig jedisPoolConfig;
	private JedisSentinelPool jedisSentinelPool;
	
	public RedisOperator() {
		jedisPoolConfig = new JedisPoolConfig();
	}
	
	public void initJedisPool(String host, int port, int timeout, String password) {
		jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
	}
	
	public void initJedisSentinelPool(String masterName, Set<String> sentinels, int timeout, String password) {
		jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, jedisPoolConfig, timeout, password);
	}
	
	/**
	 * 不通过哨兵，直接连接主机进行查询
	 * @param keys
	 * @return
	 * @author 01369626
	 * @date 2017年11月3日
	 */
	public Map<String, String> queryKeysByJedisPool(String keys) {
		Map<String, String> result = new HashMap<>();
		String[] keyArray = keys.split(",");
		Jedis jedis = jedisPool.getResource();
		for(String key : keyArray) {
			result.put(key, jedis.get(key));
		}
		jedis.close();
		return result;
	}
	
	/** 
	 * 模糊查询
	 * @param likekey
	 * @return
	 * @author 80002517
	 * @date 2017年12月1日
	 */
	public Map<String, String> likeQuery (String likeKey, String likeVal) {
	    Map<String, String> map = new HashMap<>();
	    Jedis jedis = getJedis();
	    Set<String> keys = jedis.keys(likeKey);
	    for (String  key : keys) {
	        String value = get(jedis, key);
	        if(value != null && (likeVal == null ||
	                value.indexOf(likeVal) > -1)){
	            map.put(key, get(jedis, key));
	        }
	    }
	    return map;
	}
	
	public Map<String, String> likeQueryByJedisSentinelPool (String likeKey, String likeVal) {
	        Map<String, String> map = new HashMap<>();
	        Jedis jedis = getJedisByJedisSentinelPool();
	        Set<String> keys = jedis.keys(likeKey);
	        for (String  key : keys) {
	            String value = get(jedis, key);
	            if(value != null && (likeVal == null ||
	                    value.indexOf(likeVal) > -1)){
	                map.put(key, get(jedis, key));
	            }
	        }
	        return map;
	}
	   
	private Jedis getJedis() {
	    return jedisPool.getResource();
	}
	
	private Jedis getJedisByJedisSentinelPool() {
        return jedisSentinelPool.getResource();
    }
	
	public String get(Jedis jedis, String key){
	    byte[] bytes = null;
        try {
            bytes = jedis.get(key.getBytes());
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi;
            oi = new ObjectInputStream(bi);
            return JsonUtil.writeValueAsString(oi.readObject());
        } catch (Exception e) {
            if(bytes != null){
                try {
                    return new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
	}
	
	/**
	 * 通过哨兵连接redis服务器进行查询
	 * @param keys
	 * @return
	 * @author 01369626
	 * @date 2017年11月3日
	 */
	public Map<String, String> queryKeysByJedisSentinelPool(String keys){
		Map<String, String> result = new HashMap<>();
		String[] keyArray = keys.split(",");
		Jedis jedis = jedisSentinelPool.getResource();
		for(String key : keyArray) {
			result.put(key, jedis.get(key));
		}
		jedis.close();
		return result;
	}
	
	public void delByJedisSentinelPool(String keys) {
		String[] keyArray = keys.split(",");
		Jedis jedis = jedisSentinelPool.getResource();
		jedis.del(keyArray);
		jedis.close();
	}
	
	public void delByJedisPool(String keys) {
		String[] keyArray = keys.split(",");
		Jedis jedis = jedisPool.getResource();
		jedis.del(keyArray);
		jedis.close();
	}
	
	public void close() {
		if(jedisPool != null) {
			jedisPool.destroy();
		}
		if(jedisSentinelPool != null) {
			jedisSentinelPool.destroy();
		}
	}
	
}
