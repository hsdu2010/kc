package com.sf.shiva.oms.ht.web.controller.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.domain.vo.TableRecord;
import com.sf.shiva.oms.ht.service.db.DbConnCfgService;
import com.sf.shiva.oms.ht.service.db.JdbcService;
import com.sf.shiva.oms.ht.service.db.SqlCfgService;
import com.sf.shiva.oms.ht.service.util.DBHelper;


@RequestMapping("jdbc")
@Controller
public class JdbcController {
	
	private Logger logger = LoggerFactory.getLogger(JdbcController.class);
	@Autowired
	private SqlCfgService sqlCfgService;
	@Autowired
	private DbConnCfgService dbConnCfgService;
	@Autowired
	private JdbcService jdbcService;

	@RequestMapping("query")
	@ResponseBody
	public Map<String, Object> jdbcConntion(@RequestParam("sqlId")String sqlId, @RequestParam("param")String param) {
		SqlCfg sqlCfg = sqlCfgService.selectByPrimaryKey(sqlId);
		DbConnCfg record = dbConnCfgService.selectByPrimaryKey(sqlCfg.getDbId());
		String sqlStatement = sqlCfg.getSqlStatement();
		Map<String, Object> result = new LinkedHashMap<>();
		JSONArray jsonArray = new JSONArray();
		ResultSet resultSet = null;
		DBHelper db = null;
		try {
			db = new DBHelper(record, sqlStatement);
			db.pst.setString(1, param);
			resultSet = db.pst.executeQuery();// 执行语句，得到结果集
			resultSetToJsonArray(jsonArray, resultSet);
			result = getOperResult(true, "查询成功", jsonArray);
		} catch (Exception e) {
			logger.error("JdbcController jdbcConnection error", e);
			result = getOperResult(false, e.getMessage(), null);
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(db != null) {
					db.close();// 关闭连接
				}
			} catch (SQLException e) {
				
			}
		}
		return result;
	}
	
	/**
	 * 数据库连接测试
	 * @param dbConnCfg
	 * @return
	 * @author 01369626
	 * @date 2017年10月30日
	 */
	@RequestMapping("dbConnTest")
	@ResponseBody
	public Boolean dbConnTest(DbConnCfg dbConnCfg) {
		return jdbcService.dbConnTest(dbConnCfg);
	}
	
	/**
	 * 自助快捷查询接口
	 * @param sqlId
	 * @param param
	 * @return
	 * @author 01369626
	 * @date 2017年11月2日
	 * 
	 */
	@RequestMapping("quickQuery")
	@ResponseBody
	public List<TableRecord> quickQuery(String sqlId, String param){
		return jdbcService.queryDatabase(sqlId, param);
	}
	
	/**
	 * 设置回复信息
	 * @param success 查询是否成功
	 * @param errorMsg 错误信息
	 * @param jsonArray 结果json
	 * @return
	 * @author 01369610
	 * @date 2017年10月28日
	 */
	private Map<String, Object> getOperResult(boolean success, String errorMsg, JSONArray jsonArray) {
		Map<String, Object> resultMap = new LinkedHashMap<>();
		resultMap.put("success", success);
		resultMap.put("errorMessage", errorMsg);
		resultMap.put("obj", jsonArray);
		return resultMap;
	}

	/**
	 * resultSet转换成JsonArray
	 * 
	 * @param jsonArray
	 * @param resultSet
	 * @throws SQLException
	 * @author 01369610
	 * @date 2017年10月28日
	 */
	private void resultSetToJsonArray(JSONArray jsonArray, ResultSet resultSet) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (resultSet.next()) {
			// JSONObject jsonObj = new JSONObject();
			HashMap<String, String> hashMap = new LinkedHashMap<>();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = resultSet.getString(columnName);
				hashMap.put(columnName, value);
			}
			jsonArray.add(hashMap);
		}
	}
	
}
