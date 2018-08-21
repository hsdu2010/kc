package com.sf.shiva.oms.ht.web.controller.db;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.DbConnCfgExample;
import com.sf.shiva.oms.ht.service.db.DbConnCfgService;
import com.sf.shiva.oms.ht.service.db.SqlDBCfgVOService;

/**
 * DbConnCfgController类
 *
 * @author 01369626
*/
@RequestMapping("dbConnCfg")
@Controller
public class DbConnCfgController {

	@Autowired
    private DbConnCfgService dbConnCfgService;
	@Autowired
	private SqlDBCfgVOService sqlDBCfgVOService;
    
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
	public Integer countByExample(DbConnCfgExample example){
		int count = 0;
	    count = dbConnCfgService.countByExample(example);
	    return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param dbId id
	 * 
	 * @return int 成功条数  
	 *
    */
	@RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public Boolean deleteByPrimaryKey(String dbId){
    	if(dbId == null || "".equals(dbId.trim())){
    		return false;
    	}
    	int count = 0;
    	count = dbConnCfgService.deleteByPrimaryKey(dbId);
    	sqlDBCfgVOService.deleteSqlByDbId(dbId);
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
    public Boolean insert(DbConnCfg record){
    	if(record == null){
			return false;
		}
    	int count = 0;
    	count = dbConnCfgService.insert(record);
        return count > 0;
    }
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param offset 起始条数偏移量
	 * @param pageSize  每页数量
	 * 
	 * @return  List<DbConnCfg> 记录列表 
	 *
    */	
	@RequestMapping("selectByExample")
    @ResponseBody
    public Map<String, Object> selectByExample(DbConnCfg record, Integer offset, Integer pageSize){
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
    	DbConnCfgExample example = new DbConnCfgExample(); 
    	Page<DbConnCfg> rows = null;
		rows = dbConnCfgService.selectByExample(example, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
        return resultMap;
    }

	/**
	 * 按主键查询
	 * 
	 * @param dbId id
	 * 
	 * @return DbConnCfg 记录 
	 *
    */	
	@RequestMapping("selectByPrimaryKey")
    @ResponseBody
	public DbConnCfg selectByPrimaryKey(String dbId){
    	if(dbId == null || "".equals(dbId.trim())){
    		throw new IllegalStateException("参数dbId不能为空");
    	}
	    return dbConnCfgService.selectByPrimaryKey(dbId);
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
	public Boolean updateByPrimaryKeySelective(DbConnCfg record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getDbId() == null || "".equals(record.getDbId().trim())){
    		throw new IllegalStateException("主键dbId属性不能为空");
    	}
	    int count = 0;
		count = dbConnCfgService.updateByPrimaryKeySelective(record);
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
    public Boolean updateByPrimaryKey(DbConnCfg record){
    	if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getDbId() == null || "".equals(record.getDbId().trim())){
    		throw new IllegalStateException("主键dbId属性不能为空");
    	}
    	int count = 0;
    	count = dbConnCfgService.updateByPrimaryKey(record);
        return count > 0;
    }
	
	/**
	 * 模糊查询，根据连接名获取记录
	 * @param connName
	 * @param offset
	 * @param pageSize
	 * @return
	 * @author 01369626
	 * @date 2017年12月1日
	 */
	@RequestMapping("searchByConnName")
	@ResponseBody
	public Map<String, Object> searchByConnName(String connName, Integer offset, Integer pageSize){
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
		Page<DbConnCfg> rows = null;
		rows = dbConnCfgService.searchByConnName(connName, pageNum, pageSize);
		resultMap.put("rows", rows.getResult());
		resultMap.put("totalRecord", rows.getTotal());
		return resultMap;
	}
	
}