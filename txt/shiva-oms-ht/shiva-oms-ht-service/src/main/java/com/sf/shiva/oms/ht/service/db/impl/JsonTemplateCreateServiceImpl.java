package com.sf.shiva.oms.ht.service.db.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.vo.JsonTemplate;
import com.sf.shiva.oms.ht.manager.JsonCreateTemplateManager;
import com.sf.shiva.oms.ht.service.db.JsonTemplateCreateService;
import com.sf.shiva.oms.ht.util.UserManager;

@Component
public class JsonTemplateCreateServiceImpl implements JsonTemplateCreateService {

	private static final Logger logger = LoggerFactory.getLogger(JsonTemplateCreateServiceImpl.class);
	
	@Autowired
	private JsonCreateTemplateManager orderCreateTemplateManager;
	
	@Override
	public Page<JsonTemplate> findAllTemplate(Integer pageNum, Integer pageSize, String templateType) {
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
		Page<JsonTemplate> records = new Page<>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<JsonTemplate>)orderCreateTemplateManager.selectAll(UserManager.getCurrentUserNo(), templateType);
		}catch (Exception e) {
			logger.error("findAllTemplate error:{}",e);
		}
		return records;
	}

	@Override
	public boolean update(JsonTemplate orderCreateTemplate) {
		try {
			orderCreateTemplate.setModifyEmp(UserManager.getCurrentUserNo());
			orderCreateTemplateManager.update(orderCreateTemplate);
		}catch(Exception e) {
			logger.error(" orderCreateTemplate update error : {} " ,e );
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteById(String id) {
		try {
			orderCreateTemplateManager.deleteById(id);
		}catch(Exception e) {
			logger.error(" orderCreateTemplate delete error : {} " ,e );
			return false;
		}
		return true;
	}

	@Override
	public boolean insert(JsonTemplate orderCreateTemplate) {
		try {
			orderCreateTemplate.setId(UUID22.getUUID22());
			orderCreateTemplate.setCreateEmp(UserManager.getCurrentUserNo());
			orderCreateTemplateManager.insert(orderCreateTemplate);
		}catch(Exception e) {
			logger.error(" orderCreateTemplate insert error : {} " ,e );
			return false;
		}
		return true;
	}

	@Override
	public JsonTemplate findTemplateById(String id) {
		try {
			return orderCreateTemplateManager.findTemplateById(id);
		}catch(Exception e) {
			logger.error(" orderCreateTemplate findTemplateById error : {} " ,e );
		}
		return null;
	}
}
