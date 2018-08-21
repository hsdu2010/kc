package com.sf.shiva.oms.ht.web.controller.db;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.domain.SqlCfgExample;
import com.sf.shiva.oms.ht.service.db.SqlCfgService;

/**
 * SqlCfgController类
 *
 * @author 01369626
*/
@RequestMapping("sqlCfg")
@Controller
public class SqlCfgController {

	@Autowired
    private SqlCfgService sqlCfgService;
    
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 *
	 * @return int 记录总数
	 *
    */
    @RequestMapping("countByExample")
    @ResponseBody
	public Integer countByExample(SqlCfgExample example){
		int count = 0;
	    count = sqlCfgService.countByExample(example);
	    return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public Boolean deleteByPrimaryKey(String sqlId){
    	if(sqlId == null || "".equals(sqlId.trim())){
    		throw new IllegalStateException("参数sqlId不能为空");
    	}
    	int count = 0;
    	count = sqlCfgService.deleteByPrimaryKey(sqlId);
        return  count > 0;
    }

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("insert")
    @ResponseBody
    public Boolean insert(SqlCfg record){
    	if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	int count = 0;
    	count = sqlCfgService.insert(record);
        return  count > 0;
    }
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param offset 起始条数偏移量
	 * @param pageSize  每页数量
	 * 
	 * @return  List<SqlCfg> 记录列表 
	 *
    */	
	@RequestMapping("selectByExample")
    @ResponseBody
    public Map<String, Object> selectByExample(SqlCfg record, Integer offset, Integer pageSize){
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
    	SqlCfgExample example = new SqlCfgExample(); 
    	Page<SqlCfg> rows = null;
		rows = sqlCfgService.selectByExample(example, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
        return resultMap;
    }

	/**
	 * 按主键查询
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return SqlCfg 记录 
	 *
    */	
	@RequestMapping("selectByPrimaryKey")
    @ResponseBody
	public SqlCfg selectByPrimaryKey(String sqlId){
	    return sqlCfgService.selectByPrimaryKey(sqlId);
	}
	
	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
     *
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("updateByPrimaryKeySelective")
    @ResponseBody
	public Boolean updateByPrimaryKeySelective(SqlCfg record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getSqlId() == null || "".equals(record.getSqlId().trim())){
    		throw new IllegalStateException("主键sqlId属性不能为空");
    	}
	    int count = 0;
		count = sqlCfgService.updateByPrimaryKeySelective(record);
	    return count > 0; 
	}

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
     *
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("updateByPrimaryKey")
    @ResponseBody
    public Boolean updateByPrimaryKey(SqlCfg record){
    	if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getSqlId() == null || "".equals(record.getSqlId().trim())){
    		throw new IllegalStateException("主键sqlId属性不能为空");
    	}
    	int count = 0;
    	count = sqlCfgService.updateByPrimaryKey(record);
        return count > 0;
    }
	
	@RequestMapping("searchBySqlName")
	@ResponseBody
	public Map<String, Object> searchBySqlName(String sqlName, Integer offset, Integer pageSize){
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
		Map<String, Object> resultMap = new HashMap<>();
		Page<SqlCfg> rows = null;
		rows = sqlCfgService.searchBySqlName(sqlName, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
		return resultMap;
	}
}