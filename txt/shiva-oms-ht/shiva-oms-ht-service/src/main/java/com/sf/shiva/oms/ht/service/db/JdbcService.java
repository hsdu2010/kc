package com.sf.shiva.oms.ht.service.db;

import java.util.List;

import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.vo.TableRecord;

public interface JdbcService {
	
	/**
	 * 测试数据库连接
	 * @param dbConnCfg
	 * @return
	 * @author 01369626
	 * @date 2017年10月30日
	 */
	public Boolean dbConnTest(DbConnCfg dbConnCfg);
	
	/**
	 * 根据sql id从数据库查询信息并返回记录
	 * @param sqlId
	 * @param param
	 * @return
	 * @author 01369626
	 * @date 2017年11月1日
	 */
	public List<TableRecord> queryDatabase(String sqlId, String param);
	
}
