package com.sf.shiva.oms.ht.web.controller.db.extend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.vo.SqlDBCfgVO;
import com.sf.shiva.oms.ht.service.db.SqlDBCfgVOService;

@RequestMapping("sqlCfg")
@Controller
public class SqlCfgExtendController {
	
	@Autowired
	private SqlDBCfgVOService sqlDBCfgVOService;
	
	@RequestMapping("selectSqlDBCfg")
	@ResponseBody
	public Map<String, Object> selectSqlDBCfg(Integer offset, Integer pageSize){
		if(offset != null && offset < 0){
			throw new IllegalStateException("参数offset不能小于0");
		}
		if(pageSize != null && pageSize < 1){
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if((offset == null && pageSize != null)
			||(offset != null && pageSize == null)){
			throw new IllegalStateException("offset、pageSize必须同时为null或不为null");
		}
		Integer pageNum = null;
		if(pageSize != null){
			pageNum = (offset.intValue() / pageSize.intValue()) + 1;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Page<SqlDBCfgVO> rows = sqlDBCfgVOService.selectSqlDBCfg(pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
        return resultMap;
	}
	
	@RequestMapping("selectSqlDBCfgById")
	@ResponseBody
	public SqlDBCfgVO selectSqlDBCfgById(String sqlId) {
		return sqlDBCfgVOService.selectSqlDBCfgById(sqlId);
	}
}
