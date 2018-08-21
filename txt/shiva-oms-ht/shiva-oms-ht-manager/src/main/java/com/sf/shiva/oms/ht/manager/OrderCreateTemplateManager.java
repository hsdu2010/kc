package com.sf.shiva.oms.ht.manager;

import java.util.List;

import com.sf.shiva.oms.ht.domain.vo.OrderCreateTemplate;

public interface OrderCreateTemplateManager {
	
	/**
	 * 获取订单生成模板
	 * @param createEmp 用户名称
	 * @return
	 */
	List<OrderCreateTemplate> selectAll(String createEmp);
	
	/**
	 * 根据主键删除模板表数据
	 * @param id
	 */
	void deleteById(String id);
	
	/**
	 * 数据插入
	 * @param orderCreateTemplate
	 */
	void insert(OrderCreateTemplate orderCreateTemplate);
	
	/**
	 * 数据更新
	 * @param orderCreateTemplate
	 */
	void update(OrderCreateTemplate orderCreateTemplate);
	
	/**
	 * 根据id获取对应的模板信息
	 * @param id
	 * @return
	 */
	OrderCreateTemplate findTemplateById(String id);
}
