package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.DbConnCfg;

public interface DbConnCfgExtendMapper {
	
	List<DbConnCfg> selectAll();
	
	/**
	 * 模糊查询，根据连接名获取记录
	 * @param connName
	 * @return
	 * @author 01369626
	 * @date 2017年12月1日
	 */
	List<DbConnCfg> searchByConnName(String connName);
}
