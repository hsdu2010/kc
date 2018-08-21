package com.sf.shiva.oms.ht.service.redis.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.dto.RedisOperateDto;
import com.sf.shiva.oms.ht.service.contants.Constants;
import com.sf.shiva.oms.ht.service.redis.RedisOperService;
import com.sf.shiva.oms.ht.service.redis.utils.RedisOperator;

/**
 * 
 * 描述：redis操作实现类
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
@Service
public class RedisOperServiceImpl implements RedisOperService{
	
	private static final Logger logger = LoggerFactory.getLogger(RedisOperServiceImpl.class);

	@Override
	public Result<Map<String, String>> queryRedis(RedisOperateDto dto) {
		Result<Map<String, String>> result = new Result<>();
		RedisOperator redisOperator = new RedisOperator();
		try {
			if(dto.getUseSentinel() == Constants.SENTINEL_FLAG) {//使用哨兵连接
				Set<String> sentinels = getSentinels(dto.getAddress());
				redisOperator.initJedisSentinelPool(dto.getMasterName(), sentinels, dto.getTimeout(), dto.getPassword());
				result.setObj(redisOperator.likeQueryByJedisSentinelPool(dto.getQueryKeys(), dto.getQueryValues()));
				result.setSuccess(true);
			}else {//直连redis主机
				String[] linkArray = dto.getAddress().split(":");
				String host = linkArray[0];
				int port = Integer.parseInt(linkArray[1]);
				redisOperator.initJedisPool(host, port, dto.getTimeout(), dto.getPassword());
				result.setObj(redisOperator.likeQuery(dto.getQueryKeys(), dto.getQueryValues()));
				result.setSuccess(true);
			}
		}catch (Exception e) {
		    logger.error("RedisOperServiceImpl queryRedis error.", e);
			result.setSuccess(false);
			result.setErrorMessage("查询失败，请检查redis配置");
		}finally {
			redisOperator.close();
		}
		return result;
	}

	@Override
	public Result<String> deleteRedisByKeys(RedisOperateDto dto) {
		Result<String> result = new Result<>();
		RedisOperator redisOperator = new RedisOperator();
		try {
			if(dto.getUseSentinel() == Constants.SENTINEL_FLAG) {//使用哨兵连接
				Set<String> sentinels = getSentinels(dto.getAddress());
				redisOperator.initJedisSentinelPool(dto.getMasterName(), sentinels, dto.getTimeout(), dto.getPassword());
				redisOperator.delByJedisSentinelPool(dto.getQueryKeys());
				result.setSuccess(true);
			}else {
				String[] linkArray = dto.getAddress().split(":");
				String host = linkArray[0];
				int port = Integer.parseInt(linkArray[1]);
				redisOperator.initJedisPool(host, port, dto.getTimeout(), dto.getPassword());
				redisOperator.delByJedisPool(dto.getQueryKeys());
				result.setSuccess(true);
			}
		}catch (Exception e) {
			logger.error("RedisOperServiceImpl deleteRedisByKeys error.{}",e);
			result.setErrorMessage("删除失败");
		}finally {
			redisOperator.close();
		}
		return result;
	}
	
	private Set<String> getSentinels(String addressStr){
		String[] address = addressStr.split(",");
		Set<String> sentinels = new HashSet<>();
		for(String sentinel : address) {
			sentinels.add(sentinel);
		}
		return sentinels;
	}
	
}
