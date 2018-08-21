package com.sf.shiva.oms.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sf.shiva.oms.ht.domain.vo.OrderCreateTemplate;

public interface OrderCreateMapper {
	
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
	 * @param OrderCreateTemplate
	 */
	void insert(@Param("record")OrderCreateTemplate orderCreateTemplate);
	
	/**
	 * 数据更新
	 * @param OrderCreateTemplate
	 */
	void update(@Param("record")OrderCreateTemplate orderCreateTemplate);
	
	/**
	 * 根据id获取模板信息
	 * @param id
	 * @return
	 */
	OrderCreateTemplate findTemplateById(String id); 
}
