package com.sf.shiva.oms.ht.web.controller.redis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.dto.RedisOperateDto;
import com.sf.shiva.oms.ht.service.redis.RedisOperService;

/**
 * 
 * 描述：redis操作Controller
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
@Controller
@RequestMapping("redis")
public class RedisOperController {
	
	@Autowired
	private RedisOperService redisOperService;
	
	@RequestMapping("queryRedis")
	@ResponseBody
	public Result<Map<String, String>> queryRedis(RedisOperateDto dto){
		return redisOperService.queryRedis(dto);
	}
	
	@RequestMapping("delRedis")
	@ResponseBody
	public Result<String> delRedis(RedisOperateDto dto){
		return redisOperService.deleteRedisByKeys(dto);
	}
	
}
