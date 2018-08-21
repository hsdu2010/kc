package com.sf.shiva.oms.ht.service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sf.shiva.oms.ht.domain.DbConnCfg;

/**
 * 
 * 描述：数据库DBHelper
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月28日      01369610         Create
 * ****************************************************************************
 * </pre>
 * @author 01369610
 * @since 1.0
 */
public class DBHelper {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
	public Connection conn = null;
	public PreparedStatement pst = null;
	
	public DBHelper(DbConnCfg record) throws Exception{
		Class.forName(DBDRIVER);
		String url = "jdbc:mysql://".concat(record.getHost()).concat(":").concat(record.getPort().toString())
				.concat("/").concat(record.getDbName());
		String userName = record.getUsername();
		String passWord = record.getPassword();
		conn = DriverManager.getConnection(url, userName, passWord);// 获取连接
	}

	public DBHelper(DbConnCfg record, String sql) throws Exception {
		this(record);
		pst = conn.prepareStatement(sql);// 准备执行语句
		
	}

	public void close() {
		try {
			if(pst != null) {
				this.pst.close();
			}
			if(conn != null) {
				this.conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
