package com.sf.shiva.oms.ht.service.db.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sf.shiva.oms.ht.common.utils.UUID22;
import com.sf.shiva.oms.ht.domain.vo.OrderCreateTemplate;
import com.sf.shiva.oms.ht.manager.OrderCreateTemplateManager;
import com.sf.shiva.oms.ht.service.db.OrderCreateService;
import com.sf.shiva.oms.ht.util.UserManager;

@Component
public class OrderCreateServiceImpl implements OrderCreateService {

	private static final Logger logger = LoggerFactory.getLogger(OrderCreateServiceImpl.class);
	
	@Autowired
	private OrderCreateTemplateManager orderCreateTemplateManager;
	
	@Override
	public Page<OrderCreateTemplate> selectOrderCreateTemplate(Integer pageNum, Integer pageSize) {
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
		Page<OrderCreateTemplate> records = new Page<>();
		PageHelper.startPage(pageNum, pageSize);
		try {
			records = (Page<OrderCreateTemplate>)orderCreateTemplateManager.selectAll(UserManager.getCurrentUserNo());
		}catch (Exception e) {
			logger.error("selectOrderCreateTemplate error:{}",e);
		}
		return records;
	}

	@Override
	public boolean update(OrderCreateTemplate orderCreateTemplate) {
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
	public boolean insert(OrderCreateTemplate orderCreateTemplate) {
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
	public OrderCreateTemplate findTemplateById(String id) {
		try {
			return orderCreateTemplateManager.findTemplateById(id);
		}catch(Exception e) {
			logger.error(" orderCreateTemplate findTemplateById error : {} " ,e );
		}
		return null;
	}
}
