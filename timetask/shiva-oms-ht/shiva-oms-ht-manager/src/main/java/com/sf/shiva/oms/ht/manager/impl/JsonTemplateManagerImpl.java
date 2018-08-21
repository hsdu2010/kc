package com.sf.shiva.oms.ht.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.vo.JsonTemplate;
import com.sf.shiva.oms.ht.manager.JsonCreateTemplateManager;
import com.sf.shiva.oms.ht.mapper.JsonTemplateMapper;

@Component
public class JsonTemplateManagerImpl implements JsonCreateTemplateManager {

	@Autowired
	private JsonTemplateMapper orderCreateMapper;
	
	@Override
	public List<JsonTemplate> selectAll(String createEmp, String templateType) {
		return orderCreateMapper.selectAll(createEmp, templateType);
	}

	@Override
	public void deleteById(String id) {
		orderCreateMapper.deleteById(id);
	}

	@Override
	public void insert(JsonTemplate orderCreateTemplate) {
		orderCreateMapper.insert(orderCreateTemplate);
	}

	@Override
	public void update(JsonTemplate orderCreateTemplate) {
		orderCreateMapper.update(orderCreateTemplate);
	}

	@Override
	public JsonTemplate findTemplateById(String id) {
		return orderCreateMapper.findTemplateById(id);
	}

}
