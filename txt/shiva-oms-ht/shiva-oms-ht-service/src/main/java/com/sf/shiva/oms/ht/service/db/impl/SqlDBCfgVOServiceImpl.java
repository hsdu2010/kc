package com.sf.shiva.oms.ht.service.db.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.domain.SqlCfgExample;
import com.sf.shiva.oms.ht.domain.vo.SqlDBCfgVO;
import com.sf.shiva.oms.ht.manager.SqlCfgManager;
import com.sf.shiva.oms.ht.manager.extend.SqlDBCfgVOExtendManager;
import com.sf.shiva.oms.ht.service.db.SqlDBCfgVOService;

@Service
public class SqlDBCfgVOServiceImpl implements SqlDBCfgVOService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlDBCfgVOExtendManager sqlDBCfgVOExtendManager;
	@Autowired
	private SqlCfgManager sqlCfgManager;

	@Override
	public Page<SqlDBCfgVO> selectSqlDBCfg(Integer pageNum, Integer pageSize) {
		if(pageNum != null && pageNum < 1){
			throw new IllegalStateException("参数pageNum不能小于1");
		}
		if(pageSize != null && pageSize < 1){
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if((pageNum == null && pageSize != null)
			||(pageNum != null && pageSize == null)){
			throw new IllegalStateException("pageNum、pageSize必须同时为null或不为null");
		}
		if(pageNum == null && pageSize == null){ //一次查所有数据
			pageNum = 1;
			pageSize = 0;
		}
		Page<SqlDBCfgVO> records = new Page<>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<SqlDBCfgVO>)sqlDBCfgVOExtendManager.selectSqlDBCfg();
		}catch (Exception e) {
			logger.error("selectSqlDBCfg error:{}",e);
		}
		return records;
	}

	@Override
	public SqlDBCfgVO selectSqlDBCfgById(String sqlId) {
		if(sqlId == null || "".equals(sqlId.trim())){
    		throw new IllegalStateException("参数sqlId不能为空");
    	}
		SqlDBCfgVO sqlDBCfgVO = null;
		try {
			sqlDBCfgVO = sqlDBCfgVOExtendManager.selectSqlDBCfgById(sqlId);
		}catch (Exception e) {
			logger.error("selectSqlDBCfgById error:{}",e);
		}
		return sqlDBCfgVO;
	}

	@Override
	public int deleteSqlByDbId(String dbId) {
		SqlCfgExample sqlCfgExample = new SqlCfgExample();
		sqlCfgExample.createCriteria().andDbIdEqualTo(dbId);
		return sqlCfgManager.deleteByExample(sqlCfgExample);
	}

}
