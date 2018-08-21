package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.RedisCfg;

public interface RedisCfgExtMapper {
	
	public List<RedisCfg> searchByModuleName(String moduleName);
}
