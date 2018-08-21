package com.sf.shiva.oms.ht.service.db.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.SqlCfg;
import com.sf.shiva.oms.ht.domain.SqlCfgExample;
import com.sf.shiva.oms.ht.manager.SqlCfgManager;
import com.sf.shiva.oms.ht.manager.extend.SqlCfgExtendManager;
import com.sf.shiva.oms.ht.service.db.SqlCfgService;
import com.sf.shiva.oms.ht.util.UserManager;
/**
 * SqlCfgService实现类
 *
 * @author 01369626
*/
@Service
public class SqlCfgServiceImpl implements SqlCfgService {

	private Logger logger = LoggerFactory.getLogger(getClass());
			
	@Autowired
	private SqlCfgManager sqlCfgManager;
	@Autowired
	private SqlCfgExtendManager sqlCfgExtendManager;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	public Integer countByExample(SqlCfgExample example) {
		int count = 0;
		try{
			count = sqlCfgManager.countByExample(example);
		}
		catch(Exception e){
			logger.error("countByExample error", e);
		}
		return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return Integer 成功条数  
	 *
    */
    public Integer deleteByPrimaryKey(String sqlId){
    	if(sqlId == null || "".equals(sqlId.trim())){
    		throw new IllegalStateException("参数sqlId不能为空");
    	}
		int count = 0;
		try{
			count = sqlCfgManager.deleteByPrimaryKey(sqlId);
		}
		catch(Exception e){
			logger.error("deleteByPrimaryKey error", e);
		}
		return count;
	}

	/**
	 * 新增
	 * 
	 * @param record 记录
	 * 
	 * @return Integer 成功条数  
	 *
    */
    public Integer insert(SqlCfg record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
		record.setSqlId(UUID22.getUUID22());
		record.setCreateTm(new Date());
		record.setModifyTm(new Date());
		record.setCreateEmp(UserManager.getCurrentUserNo());
    	record.setModifyEmp(UserManager.getCurrentUserNo());
		int count = 0;
		try{
			count = sqlCfgManager.insert(record);
		}
		catch(Exception e){
			logger.error("insert error", e);
		}
		return count;
	}
	
	/**
	 * 按条件查询
	 * 
	 * @param example 条件
	 * @param pageNum 页码
	 * @param pageSize 每页数量
	 * 
	 * @return  Page 
	 *
    */
    public Page<SqlCfg> selectByExample(SqlCfgExample example, Integer pageNum, Integer pageSize){
		if(pageNum != null && pageNum < 1){
			throw new IllegalStateException("参数pageNum不能小于1");
		}
		if(pageSize != null && pageSize < 1){
			throw new IllegalStateException("参数pageSize不能小于1");
		}
		if((pageNum == null && pageSize != null)
			||(pageNum != null && pageSize == null)){
			throw new IllegalStateException("pageNum、pageSize必须同时为null或不为null");
		}
		if(pageNum == null && pageSize == null){ //一次查所有数据
			pageNum = 1;
			pageSize = 0;
		}
		example.setOrderByClause("sql_name");
		Page<SqlCfg> records = new Page<SqlCfg>();
		PageHelper.startPage(pageNum, pageSize);
		try{
			records = (Page<SqlCfg>)sqlCfgManager.selectByExample(example);
		}
		catch(Exception e){
			logger.error("selectByExample error", e);
		}
		return records;
	}

	/**
	 * 按主键查询
	 * 
	 * @param sqlId sql查询id
	 * 
	 * @return  List 
	 *
    */
	public SqlCfg selectByPrimaryKey(String sqlId){
    	if(sqlId == null || "".equals(sqlId.trim())){
    		throw new IllegalStateException("参数sqlId不能为空");
    	}
		SqlCfg record = null;
		try{
			record = sqlCfgManager.selectByPrimaryKey(sqlId);
		}
		catch(Exception e){
			logger.error("selectByPrimaryKey error", e);
		}
		return record;
	}

	/**
	 * 按主键更新,null的字段不更新
	 * 
	 * @param record 记录
	 * @return  List 
	 *
    */
	public Integer updateByPrimaryKeySelective(SqlCfg record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getSqlId() == null || "".equals(record.getSqlId().trim())){
    		throw new IllegalStateException("主键sqlId属性不能为空");
    	}
		int count = 0;
		record.setModifyTm(new Date());
    	record.setModifyEmp(UserManager.getCurrentUserNo());
		try{
			count = sqlCfgManager.updateByPrimaryKeySelective(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKeySelective error", e);
		}
		return count;
	}

	/**
	 * 按主键更新
	 * 
	 * @param record 记录
	 * @return Integer 成功条数  
	 *
    */
    public Integer updateByPrimaryKey(SqlCfg record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getSqlId() == null || "".equals(record.getSqlId().trim())){
    		throw new IllegalStateException("主键sqlId属性不能为空");
    	}
		int count = 0;
		try{
			count = sqlCfgManager.updateByPrimaryKey(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

	@Override
	public Page<SqlCfg> searchBySqlName(String sqlName, Integer pageNum, Integer pageSize) {
		Page<SqlCfg> records = new Page<SqlCfg>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<SqlCfg>)sqlCfgExtendManager.searchBySqlName(sqlName);
		}catch (Exception e) {
			logger.error("SqlCfgServiceImpl searchBySqlName error.",e);
		}
		return records;
	}
	
}