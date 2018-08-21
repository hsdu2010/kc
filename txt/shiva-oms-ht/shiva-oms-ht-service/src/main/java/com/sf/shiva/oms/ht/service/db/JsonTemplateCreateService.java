package com.sf.shiva.oms.ht.service.db;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.vo.JsonTemplate;

public interface JsonTemplateCreateService {
	
	/**
	 * 查询模板数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<JsonTemplate> findAllTemplate(Integer pageNum, Integer pageSize, String templateType);
	
	/**
	 * 模板数据更新
	 * @param orderCreateTemplate
	 */
	boolean update(JsonTemplate jsonTemplate);
	
	/**
	 * 根据id删除模板数据
	 * @param id
	 */
	boolean deleteById(String id);
	
	/**
	 * 插入数据
	 * @param orderCreateTemplate
	 */
	boolean insert(JsonTemplate jsonTemplate);
	
	/**
	 * 根据id获取对应的模板信息
	 * @param id
	 * @return
	 */
	JsonTemplate findTemplateById(String id);
}
