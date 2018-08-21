package com.sf.shiva.oms.ht.domain.vo;

import java.util.List;

/**
 * 
 * 描述：jdbc数据库查询结果保存实体类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月1日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class TableRecord {
	
	/**表名*/
	private String tableName;
	/**表头*/
	private List<String> tableHeaders;
	/**表记录*/
	private List<List<String>> records;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<String> getTableHeaders() {
		return tableHeaders;
	}
	public void setTableHeaders(List<String> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}
	public List<List<String>> getRecords() {
		return records;
	}
	public void setRecords(List<List<String>> records) {
		this.records = records;
	}
	@Override
	public String toString() {
		return "TableRecord [tableHeaders=" + tableHeaders + ", records=" + records + "]";
	}
	
	
}
