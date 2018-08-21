package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.vo.SqlDBCfgVO;

public interface SqlDBCfgVOExtendMapper {
	public List<SqlDBCfgVO> selectSqlDBCfg();
	public SqlDBCfgVO selectSqlDBCfgById(String sqlId);
}
