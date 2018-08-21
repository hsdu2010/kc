package com.sf.shiva.oms.ht.manager;

import java.util.List;

import com.sf.shiva.oms.ht.domain.vo.JsonTemplate;

public interface JsonCreateTemplateManager {
	
	/**
	 * 获取订单生成模板
	 * @param createEmp 用户名称
	 * @return
	 */
	List<JsonTemplate> selectAll(String createEmp, String templateType);
	
	/**
	 * 根据主键删除模板表数据
	 * @param id
	 */
	void deleteById(String id);
	
	/**
	 * 数据插入
	 * @param orderCreateTemplate
	 */
	void insert(JsonTemplate orderCreateTemplate);
	
	/**
	 * 数据更新
	 * @param orderCreateTemplate
	 */
	void update(JsonTemplate orderCreateTemplate);
	
	/**
	 * 根据id获取对应的模板信息
	 * @param id
	 * @return
	 */
	JsonTemplate findTemplateById(String id);
}
