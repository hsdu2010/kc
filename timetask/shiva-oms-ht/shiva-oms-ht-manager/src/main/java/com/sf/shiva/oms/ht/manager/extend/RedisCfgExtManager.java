package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.RedisCfg;

public interface RedisCfgExtManager {
	
	public List<RedisCfg> searchByModuleName(String moduleName);
}
