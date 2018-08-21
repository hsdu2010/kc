package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.manager.extend.SqlCfgExtendManager;
import com.sf.shiva.oms.ht.mapper.extend.SqlCfgExtendMapper;

@Service
public class SqlCfgExtendManagerImpl implements SqlCfgExtendManager{
	@Autowired
	private SqlCfgExtendMapper sqlConnCfgExtendMapper;
	
	@Override
	public List<SqlCfg> selectAll() {
		return sqlConnCfgExtendMapper.selectAll();
	}

	@Override
	public List<SqlCfg> searchBySqlName(String sqlName) {
		return sqlConnCfgExtendMapper.searchBySqlName(sqlName);
	}
}
