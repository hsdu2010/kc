package com.sf.shiva.oms.ht.service.db;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.vo.OrderCreateTemplate;

public interface OrderCreateService {
	
	/**
	 * 查询模板数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<OrderCreateTemplate> selectOrderCreateTemplate(Integer pageNum, Integer pageSize);
	
	/**
	 * 模板数据更新
	 * @param orderCreateTemplate
	 */
	boolean update(OrderCreateTemplate orderCreateTemplate);
	
	/**
	 * 根据id删除模板数据
	 * @param id
	 */
	boolean deleteById(String id);
	
	/**
	 * 插入数据
	 * @param orderCreateTemplate
	 */
	boolean insert(OrderCreateTemplate orderCreateTemplate);
	
	/**
	 * 根据id获取对应的模板信息
	 * @param id
	 * @return
	 */
	OrderCreateTemplate findTemplateById(String id);
}
