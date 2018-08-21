package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.RedisCfg;
import com.sf.shiva.oms.ht.manager.extend.RedisCfgExtManager;
import com.sf.shiva.oms.ht.mapper.extend.RedisCfgExtMapper;

@Service
public class RedisCfgExtManagerImpl implements RedisCfgExtManager{

	@Autowired
	private RedisCfgExtMapper redisCfgExtMapper;
	
	@Override
	public List<RedisCfg> searchByModuleName(String moduleName) {
		return redisCfgExtMapper.searchByModuleName(moduleName);
	}

}
