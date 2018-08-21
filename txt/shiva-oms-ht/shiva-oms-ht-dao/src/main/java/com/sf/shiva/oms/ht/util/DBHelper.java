package com.sf.shiva.oms.ht.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.vo.TableRecord;

/**
 * 
 * 描述：数据库操作类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月2日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(DBHelper.class);
	
	private static final String DRIVER_STR = "com.mysql.jdbc.Driver";
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * 初始化数据库连接
	 * @param dbConnCfg
	 * @throws Exception
	 * @author 01369626
	 * @date 2017年11月6日
	 */
	public void init(DbConnCfg dbConnCfg) throws Exception{
		Class.forName(DRIVER_STR);
		StringBuilder jdbcStr = new StringBuilder("jdbc:mysql://").append(dbConnCfg.getHost()).append(":")
				.append(dbConnCfg.getPort()).append("/").append(dbConnCfg.getDbName());
		connection = DriverManager.getConnection(jdbcStr.toString(), dbConnCfg.getUsername(), dbConnCfg.getPassword());
	}
	
	/**
	 * 执行sql查询，多条sql语句用分号";"分割
	 * @param sql
	 * @param param
	 * @return
	 * @throws Exception
	 * @author 01369626
	 * @date 2017年11月6日
	 */
	public TableRecord executeQuery(String sql, String param) throws Exception{
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, param);
			rs = ps.executeQuery();
			return fillTableRecord(rs);
		}catch (Exception e) {
			throw e;
		}finally {
			if(rs != null) {
				rs.close();
			}
		}
	}
	
	/**
	 * 关闭数据库连接
	 * 
	 * @author 01369626
	 * @date 2017年11月6日
	 */
	public void close(){
		try {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("DBHelper close error.{}",e);
		}
	}
	
	/**
	 * 根据查询结果ResultSet获取表名，字段名和表数据
	 * @param rs
	 * @return
	 * @throws Exception
	 * @author 01369626
	 * @date 2017年11月6日
	 */
	private TableRecord fillTableRecord(ResultSet rs) throws Exception{
		TableRecord table = new TableRecord();
		List<List<String>> records = new ArrayList<>();
		List<String> row = null;
		table.setTableHeaders(getTableHeader(rs.getMetaData()));
		while(rs.next()) {
			row = new ArrayList<>();
			for(String header : table.getTableHeaders()) {
				row.add(rs.getString(header));
			}
			records.add(row);
		}
		table.setRecords(records);
		table.setTableName(rs.getMetaData().getTableName(1));
		return table;
	}
	
	private List<String> getTableHeader(ResultSetMetaData rsmd) throws Exception{
		List<String> list = new ArrayList<>();
		for(int i = 1; i <= rsmd.getColumnCount(); i++) {
			list.add(rsmd.getColumnName(i));
		}
		return list;
	}
}
