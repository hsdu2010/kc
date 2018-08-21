package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.RpcManager;

public interface RpcManagerExtendMapper {
	/**
	 * 根据应用名进行模糊查询
	 * @param appName
	 * @return
	 * @author 01369626
	 * @date 2017年12月4日
	 */
	public List<RpcManager> searchByAppName(String appName);
}
