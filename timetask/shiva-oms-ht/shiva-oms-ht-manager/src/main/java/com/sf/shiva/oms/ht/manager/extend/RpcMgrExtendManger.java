package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.RpcManager;

public interface RpcMgrExtendManger {
	
	/**
	 * 
	 * @param appName
	 * @return
	 * @author 01369626
	 * @date 2017年12月4日
	 */
	public List<RpcManager> searchByAppName(String appName);
}
