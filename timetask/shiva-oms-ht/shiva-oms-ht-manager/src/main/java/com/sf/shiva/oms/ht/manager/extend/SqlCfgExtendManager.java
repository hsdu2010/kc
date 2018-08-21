package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.SqlCfg;

public interface SqlCfgExtendManager {
	/**
	 * 查询所有数据库信息
	 * @return
	 * @author 01369610
	 * @date 2017年10月27日
	 */
	List<SqlCfg> selectAll();
	
	/**
	 * 
	 * @param sqlName
	 * @return
	 * @author 01369626
	 * @date 2017年12月1日
	 */
	List<SqlCfg> searchBySqlName(String sqlName);
}
