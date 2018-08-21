package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.vo.SqlDBCfgVO;
import com.sf.shiva.oms.ht.manager.extend.SqlDBCfgVOExtendManager;
import com.sf.shiva.oms.ht.mapper.extend.SqlDBCfgVOExtendMapper;

@Component
public class SqlDBCfgVOExtendManagerImpl implements SqlDBCfgVOExtendManager {
	
	@Autowired
	private SqlDBCfgVOExtendMapper sqlDBCfgVOExtendMapper;

	@Override
	public List<SqlDBCfgVO> selectSqlDBCfg() {
		return sqlDBCfgVOExtendMapper.selectSqlDBCfg();
	}

	@Override
	public SqlDBCfgVO selectSqlDBCfgById(String sqlId) {
		return sqlDBCfgVOExtendMapper.selectSqlDBCfgById(sqlId);
	}

}
