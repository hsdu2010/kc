package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.manager.extend.DbConnCfgExtendManager;
import com.sf.shiva.oms.ht.mapper.extend.DbConnCfgExtendMapper;

@Service
public class DbConnCfgExtendManagerImpl implements DbConnCfgExtendManager{
	@Autowired
	private DbConnCfgExtendMapper dbConnCfgExtendMapper;
	
	@Override
	public List<DbConnCfg> selectAll() {
		return dbConnCfgExtendMapper.selectAll();
	}

	@Override
	public List<DbConnCfg> selectByConnName(String connName) {
		return dbConnCfgExtendMapper.searchByConnName(connName);
	}

}
