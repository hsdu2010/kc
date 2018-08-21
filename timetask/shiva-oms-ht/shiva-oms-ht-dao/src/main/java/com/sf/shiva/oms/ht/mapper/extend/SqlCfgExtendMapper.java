package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.SqlCfg;

public interface SqlCfgExtendMapper {
	
	/**
	 * 获取全部记录
	 * @return
	 * @author 01369626
	 * @date 2017年12月1日
	 */
	List<SqlCfg> selectAll();
	
	/**
	 * 根据查询名模糊查询相应记录
	 * @param sqlName
	 * @return
	 * @author 01369626
	 * @date 2017年12月1日
	 */
	List<SqlCfg> searchBySqlName(String sqlName);
}
