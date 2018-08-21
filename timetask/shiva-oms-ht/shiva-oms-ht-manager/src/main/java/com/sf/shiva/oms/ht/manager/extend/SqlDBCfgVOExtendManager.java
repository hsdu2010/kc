package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.vo.SqlDBCfgVO;

public interface SqlDBCfgVOExtendManager {
	
	public List<SqlDBCfgVO> selectSqlDBCfg();
	
	public SqlDBCfgVO selectSqlDBCfgById(String sqlId);
	
}
