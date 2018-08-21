package com.sf.shiva.oms.ht.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sf.shiva.oms.ht.common.utils.JsonUtil;
import com.sf.shiva.oms.ht.dto.OrderCreateDto;
import com.sf.shiva.oms.ht.service.util.RestRequestUtil;
 
/**
 * 订单生成控制器
 * @author 80002031
 *
 */
@RequestMapping("orderCreate")
@Controller
public class OrderCreateController {
	
	@Value("${gen.simpleOrder.url:http://10.202.41.27:8886/shiva-oms-order-fast/orderService/genSimpleOrder}")
	private String genSimpleOrderUrl;
	@Value("${gen.fullOrder.url:http://10.202.41.27:8886/shiva-oms-order-fast/orderService/genFullOrder}")
	private String genFullOrderUrl;

	/**
	 * 简单生成订单功能
	 * @param dto	前端序列化对象
	 * @return	成功或者失败
	 */
	@RequestMapping("simpleCreate")
	@ResponseBody
	public String simpleCreate(OrderCreateDto dto) {
		return returnResult(RestRequestUtil.getInstance().request(JsonUtil.object2Json(dto), genSimpleOrderUrl));
	}
	
	/**
	 * 全量报文生成订单功能
	 * @param dto	前端序列化对象
	 * @return 成功或者失败
	 */
	@RequestMapping("allJsonCreate")
	@ResponseBody
	public String allJsonCreate(OrderCreateDto dto) {
		return returnResult(RestRequestUtil.getInstance().request(dto.getAllJson(), genFullOrderUrl));
	}
	 
	
	/**
	 * 返回结果
	 * @param result
	 * @param msg
	 * @return
	 */
	private String returnResult(String requestResult) {
		Boolean result = false;
		String msg = "系统处理异常";
		if (StringUtils.isNotBlank(requestResult)) {
			result = "100".equals(JsonUtil.getJsonValueByKey(requestResult, "resultCode")) ? true : false;
			msg = JsonUtil.getJsonValueByKey(requestResult, "exceptionMessage");
		}
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("result", result);
		jsonArray.add(jsonObject);
		jsonObject.put("msg", msg);
		jsonArray.add(jsonObject);
		return jsonArray.toString();
	}
}
