package com.sf.shiva.oms.ht.service.kafkamanager.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.KafkaWriteManager;
import com.sf.shiva.oms.ht.domain.KafkaWriteManagerExample;
import com.sf.shiva.oms.ht.manager.KafkaWriteManagerManager;
import com.sf.shiva.oms.ht.manager.extend.KafkaWriteMgrExtManager;
import com.sf.shiva.oms.ht.service.kafkamanager.KafkaWriteManagerService;
import com.sf.shiva.oms.ht.util.UserManager;
/**
 * KafkaWriteManagerService实现类
 *
 * @author 01369626
*/
@Service
public class KafkaWriteManagerServiceImpl implements KafkaWriteManagerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
			
	@Autowired
	private KafkaWriteManagerManager kafkaWriteManagerManager;
	@Autowired
	private KafkaWriteMgrExtManager kafkaWriteMgrExtManager;
	
	/**
	 * 按条件计数
	 * 
	 * @param example 条件
	 * 
	 * @return Integer 记录总数
	 *
    */
	public Integer countByExample(KafkaWriteManagerExample example) {
		int count = 0;
		try{
			count = kafkaWriteManagerManager.countByExample(example);
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
			count = kafkaWriteManagerManager.deleteByPrimaryKey(id);
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
    public Integer insert(KafkaWriteManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
		record.setId(UUID22.getUUID22());
		record.setOperator(UserManager.getCurrentUserNo());
		record.setCreatetime(new Date());
		record.setUpdatetime(new Date());
		int count = 0;
		try{
			count = kafkaWriteManagerManager.insert(record);
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
    public Page<KafkaWriteManager> selectByExample(KafkaWriteManagerExample example, Integer pageNum, Integer pageSize){
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
		Page<KafkaWriteManager> records = new Page<KafkaWriteManager>();
		PageHelper.startPage(pageNum, pageSize);
		try{
			records = (Page<KafkaWriteManager>)kafkaWriteManagerManager.selectByExample(example);
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
	public KafkaWriteManager selectByPrimaryKey(String id){
    	if(id == null || "".equals(id.trim())){
    		throw new IllegalStateException("参数id不能为空");
    	}
		KafkaWriteManager record = null;
		try{
			record = kafkaWriteManagerManager.selectByPrimaryKey(id);
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
	public Integer updateByPrimaryKeySelective(KafkaWriteManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
			count = kafkaWriteManagerManager.updateByPrimaryKeySelective(record);
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
    public Integer updateByPrimaryKey(KafkaWriteManager record){
		if(record == null){
			throw new IllegalStateException("参数record不能为空");
		}
    	if(record.getId() == null || "".equals(record.getId().trim())){
    		throw new IllegalStateException("主键id属性不能为空");
    	}
		int count = 0;
		try{
			count = kafkaWriteManagerManager.updateByPrimaryKey(record);
		}
		catch(Exception e){
			logger.error("updateByPrimaryKey error", e);
		}
		return count;
	}

	@Override
	public Page<KafkaWriteManager> searchByName(String name, Integer pageNum, Integer pageSize) {
		Page<KafkaWriteManager> records = new Page<KafkaWriteManager>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<KafkaWriteManager>)kafkaWriteMgrExtManager.searchByName(name);
		}catch (Exception e) {
			logger.error("KafkaWriteManagerServiceImpl searchByName error.",e);
		}
		return records;
	}
	
}