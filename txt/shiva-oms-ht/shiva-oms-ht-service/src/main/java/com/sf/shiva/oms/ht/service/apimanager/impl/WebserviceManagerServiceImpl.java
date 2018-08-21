package com.sf.shiva.oms.ht.service.apimanager.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.WebserviceManager;
import com.sf.shiva.oms.ht.domain.WebserviceManagerExample;
import com.sf.shiva.oms.ht.manager.WebserviceManagerManager;
import com.sf.shiva.oms.ht.manager.extend.WebserviceMgrExtendManager;
import com.sf.shiva.oms.ht.service.apimanager.WebserviceManagerService;
import com.sf.shiva.oms.ht.util.UserManager;
/**
 * WebserviceManagerService实现类
 *
 * @author 01369626
*/
@Service
public class WebserviceManagerServiceImpl implements WebserviceManagerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
			
	@Autowired
	private WebserviceManagerManager webserviceManagerManager;
	@Autowired
	private WebserviceMgrExtendManager webserviceMgrExtManager;
	
	
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	public Integer countByExample(WebserviceManagerExample example) {
		int count = 0;
		try{
			count = webserviceManagerManager.countByExample(example);
		}
		catch(Exception e){
			logger.error("countByExample error", e);
		}
		return count;
	}

	/**
	 * 按主键删除
	 * 
	 * @param id 
	 * 
	 * @return Integer 成功条数  
	 *
    */
    public Integer deleteByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
		int count = 0;
		try{
			count = webserviceManagerManager.deleteByPrimaryKey(id);
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
    public Integer insert(WebserviceManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
		int count = 0;
		try{
		    Date now = new Date();
		    record.setId(UUID22.getUUID22());
		    record.setCreateEmp(UserManager.getCurrentUserNo());
		    record.setCreatetime(now);
		    record.setUpdatetime(now);
			count = webserviceManagerManager.insert(record);
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
    public Page<WebserviceManager> selectByExample(WebserviceManagerExample example, Integer pageNum, Integer pageSize){
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
		Page<WebserviceManager> records = new Page<WebserviceManager>();
		PageHelper.startPage(pageNum, pageSize);
		try{
			records = (Page<WebserviceManager>)webserviceManagerManager.selectByExampleWithBLOBs(example);
		}
		catch(Exception e){
			logger.error("selectByExample error", e);
		}
		return records;
	}

	/**
	 * 按主键查询
	 * 
	 * @param id 
	 * 
	 * @return  List 
	 *
    */
	public WebserviceManager selectByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
		WebserviceManager record = null;
		try{
			record = webserviceManagerManager.selectByPrimaryKey(id);
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
	public Integer updateByPrimaryKeySelective(WebserviceManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
		    record.setModifyEmp(UserManager.getCurrentUserNo());
		    record.setUpdatetime(new Date());
			count = webserviceManagerManager.updateByPrimaryKeySelective(record);
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
    public Integer updateByPrimaryKey(WebserviceManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
		    record.setUpdatetime(new Date());
			count = webserviceManagerManager.updateByPrimaryKey(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

	@Override
	public Page<WebserviceManager> searchByName(String name, Integer pageNum, Integer pageSize) {
		Page<WebserviceManager> records = new Page<WebserviceManager>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<WebserviceManager>)webserviceMgrExtManager.searchByName(name);
		}catch (Exception e) {
			logger.error("WebserviceManagerServiceImpl searchByName error.",e);
		}
		return records;
	}

}