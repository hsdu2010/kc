package com.sf.shiva.oms.ht.service.redis;

import java.util.Map;

import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.domain.dto.RedisOperateDto;

/**
 * 
 * 描述：redis操作service接口
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
public interface RedisOperService {
	
	/**
	 * 根据Key查询redis对应的value，多个Key用,进行分割
	 * @param keys 查询key字符串
	 * @return 查询结果对应Map
	 * @author 01369626
	 * @date 2017年11月3日
	 */
	public Result<Map<String, String>> queryRedis(RedisOperateDto dto);
	
	/**
	 * 根据Key删除redis对应的value，多个Key用,进行分割
	 * @param dto
	 * @return 删除结果
	 * @author 01369626
	 * @date 2017年11月3日
	 */
	public Result<String> deleteRedisByKeys(RedisOperateDto dto);
	
	
}
