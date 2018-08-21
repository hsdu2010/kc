package com.sf.shiva.oms.ht.service.db;


import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.vo.SqlDBCfgVO;

public interface SqlDBCfgVOService {
	
	public Page<SqlDBCfgVO> selectSqlDBCfg(Integer pageNum, Integer pageSize);
	
	public SqlDBCfgVO selectSqlDBCfgById(String sqlId);
	
	public int deleteSqlByDbId(String dbId);
}
