package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.vo.OrderCreateTemplate;
import com.sf.shiva.oms.ht.manager.OrderCreateTemplateManager;
import com.sf.shiva.oms.ht.mapper.OrderCreateMapper;

@Component
public class OrderCreateTemplateManagerImpl implements OrderCreateTemplateManager {

	@Autowired
	private OrderCreateMapper orderCreateMapper;
	
	@Override
	public List<OrderCreateTemplate> selectAll(String createEmp) {
		return orderCreateMapper.selectAll(createEmp);
	}

	@Override
	public void deleteById(String id) {
		orderCreateMapper.deleteById(id);
	}

	@Override
	public void insert(OrderCreateTemplate orderCreateTemplate) {
		orderCreateMapper.insert(orderCreateTemplate);
	}

	@Override
	public void update(OrderCreateTemplate orderCreateTemplate) {
		orderCreateMapper.update(orderCreateTemplate);
	}

	@Override
	public OrderCreateTemplate findTemplateById(String id) {
		return orderCreateMapper.findTemplateById(id);
	}

}
