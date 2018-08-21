package com.sf.shiva.oms.ht.service.db.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.DbConnCfg;
import com.sf.shiva.oms.ht.domain.DbConnCfgExample;
import com.sf.shiva.oms.ht.manager.DbConnCfgManager;
import com.sf.shiva.oms.ht.manager.extend.DbConnCfgExtendManager;
import com.sf.shiva.oms.ht.service.db.DbConnCfgService;
import com.sf.shiva.oms.ht.util.UserManager;
/**
 * DbConnCfgService实现类
 *
 * @author 01369626
*/
@Service
public class DbConnCfgServiceImpl implements DbConnCfgService {

	private Logger logger = LoggerFactory.getLogger(getClass());
			
	@Autowired
	private DbConnCfgManager dbConnCfgManager;
	@Autowired
	private DbConnCfgExtendManager dbConnCfgExtendManager;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	public Integer countByExample(DbConnCfgExample example) {
		int count = 0;
		try{
			count = dbConnCfgManager.countByExample(example);
		}
		catch(Exception e){
			logger.error("countByExample error", e);
		}
		return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param dbId id
	 * 
	 * @return Integer 成功条数  
	 *
    */
    public Integer deleteByPrimaryKey(String dbId){
    	if(dbId == null || "".equals(dbId.trim())){
    		throw new IllegalStateException("参数dbId不能为空");
    	}
		int count = 0;
		try{
			count = dbConnCfgManager.deleteByPrimaryKey(dbId);
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
    public Integer insert(DbConnCfg record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	record.setDbId(UUID22.getUUID22());
    	record.setCreateTm(new Date());
    	record.setModifyTm(new Date());
    	record.setCreateEmp(UserManager.getCurrentUserNo());
    	record.setModifyEmp(UserManager.getCurrentUserNo());
		int count = 0;
		try{
			count = dbConnCfgManager.insert(record);
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
    public Page<DbConnCfg> selectByExample(DbConnCfgExample example, Integer pageNum, Integer pageSize){
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
		Page<DbConnCfg> records = new Page<DbConnCfg>();
		PageHelper.startPage(pageNum, pageSize);
		try{
			records = (Page<DbConnCfg>)dbConnCfgManager.selectByExample(example);
		}
		catch(Exception e){
			logger.error("selectByExample error", e);
		}
		return records;
	}

	/**
	 * 按主键查询
	 * 
	 * @param dbId id
	 * 
	 * @return  List 
	 *
    */
	public DbConnCfg selectByPrimaryKey(String dbId){
    	if(dbId == null || "".equals(dbId.trim())){
    		throw new IllegalStateException("参数dbId不能为空");
    	}
		DbConnCfg record = null;
		try{
			record = dbConnCfgManager.selectByPrimaryKey(dbId);
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
	public Integer updateByPrimaryKeySelective(DbConnCfg record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getDbId() == null || "".equals(record.getDbId().trim())){
    		throw new IllegalStateException("主键dbId属性不能为空");
    	}
		int count = 0;
		record.setModifyTm(new Date());
		record.setModifyEmp(UserManager.getCurrentUserNo());
		try{
			count = dbConnCfgManager.updateByPrimaryKeySelective(record);
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
    public Integer updateByPrimaryKey(DbConnCfg record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getDbId() == null || "".equals(record.getDbId().trim())){
    		throw new IllegalStateException("主键dbId属性不能为空");
    	}
		int count = 0;
		try{
			count = dbConnCfgManager.updateByPrimaryKey(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

	@Override
	public Page<DbConnCfg> searchByConnName(String dbConnName, Integer pageNum, Integer pageSize) {
		Page<DbConnCfg> records = new Page<DbConnCfg>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<DbConnCfg>)dbConnCfgExtendManager.selectByConnName(dbConnName);
		}catch (Exception e) {
			logger.error("DbConnCfgServiceImpl searchByConnName error", e);
		}
		return records;
	}
	
}