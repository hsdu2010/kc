package com.sf.shiva.oms.ht.service.db.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.domain.vo.TableRecord;
import com.sf.shiva.oms.ht.manager.DbConnCfgManager;
import com.sf.shiva.oms.ht.manager.SqlCfgManager;
import com.sf.shiva.oms.ht.service.db.JdbcService;
import com.sf.shiva.oms.ht.util.DBHelper;

@Service
public class JdbcServiceImpl implements JdbcService{
	
	private Logger logger = LoggerFactory.getLogger(JdbcServiceImpl.class);
	
	@Autowired
	private SqlCfgManager sqlCfgManager;
	@Autowired
	private DbConnCfgManager dbConnCfgManager;

	@Override
	public Boolean dbConnTest(DbConnCfg dbConnCfg) {
		DBHelper dbHelper = null;
		try {
			dbHelper = new DBHelper();
			dbHelper.init(dbConnCfg);
		}catch (Exception e) {
			return false;
		}finally {
			if(dbHelper != null) {
				dbHelper.close();
			}
		}
		return true;
	}

	@Override
	public List<TableRecord> queryDatabase(String sqlId, String param) {
		DBHelper dbHelper = null;
		List<TableRecord> results = new ArrayList<>();
		try {
			SqlCfg sqlCfg = sqlCfgManager.selectByPrimaryKey(sqlId);
			DbConnCfg dbConnCfg = dbConnCfgManager.selectByPrimaryKey(sqlCfg.getDbId());
			dbHelper = new DBHelper();
			dbHelper.init(dbConnCfg);
			String[] sqls = sqlCfg.getSqlStatement().split(";");
			TableRecord tableRecord = null;
			for(String sql :sqls) {
				tableRecord = dbHelper.executeQuery(sql, param);
				results.add(tableRecord);
			}
		}catch (Exception e) {
			logger.error("JdbcServiceImpl queryDatabase error.{}",e);
		}
		return results;
	}
	
}
