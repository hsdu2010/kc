package com.sf.shiva.oms.ht.web.controller.redis;

public class RedisTest {

	public static void main(String[] args) {
		RedisSentinelClient client = new RedisSentinelClient();
		client.initSentinelPool();
		
		System.out.println(RedisSentinelClient.getSharedJedis().get("b"));
	}

}
