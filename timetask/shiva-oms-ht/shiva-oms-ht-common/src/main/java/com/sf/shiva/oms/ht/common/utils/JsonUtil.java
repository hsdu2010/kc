package com.sf.shiva.oms.ht.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
 
 

/**
 * @description : JsonUtil工具类，<br>
 *              用于将对象转化成JSON以及将JSON 转化成对象，若发生异常，则记录错误日志信息
 * @author : 879149
 * @since : 1.0 HISTORY
 * 
 *        <pre>
 * ****************************************************************************
 *  ID   DATE            PERSON   REASON             DESC
 *  1    2016年1月28日              879149   Create/Modified    JsonUtil工具类
 *  2 	 2016年6月22日	866321		添加将JSON转为List的方法
 * ****************************************************************************
 *        </pre>
 */
public class JsonUtil {
	
	private JsonUtil(){}

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper objectMapper;
	/** 格式化时间的string */
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	static {
		objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国北京时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// 序列化时，日期的统一格式
		objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));

		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // KEY无引号处理
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}

	/**
	 * json 转换成 Object
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T json2Object(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("JsonUtil json2Object execute error", e);
			throw new RuntimeException("analysis json error");
		}
	}

	public static <T> T json2Object(String json, TypeRef<T> tr) {
		try {
			return objectMapper.readValue(json, tr.getType());
		} catch (IOException e) {
			logger.error("JsonUtil json2Object execute error", e);
			throw new RuntimeException("analysis json error", e);
		}
	}

	/**
	 * obj 转换成json
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> String object2Json(T entity) {
		try {
			return objectMapper.writeValueAsString(entity);
		} catch (IOException e) {
			logger.error("JsonUtil object2Json execute error", e);
			throw new RuntimeException("convert json error");
		}
	}

	/**
	 * obj对象 转换成树型JSON
	 * 
	 * @param obj
	 * @return
	 */
	public static JsonNode object2TreeJson(Object obj) {
		try {
			return objectMapper.valueToTree(obj);
		} catch (Exception e) {
			logger.error("JsonUtil object2TreeJson execute error", e);
			throw new RuntimeException("convert json error");
		}
	}

	 /**
     * 将JSON字符串转换成List对象
     * 
     * @param <E>
     *            类
     * @param json
     *            JSON字符串
     * @param clazz
     *            业务对象Class
     * @return List对象
     * @author 866321
     */
    public static <E> List<E> json2List(Class<E> clazz, String json) {
        if (null == clazz || StringUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        List<E> list = null;
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class,
                clazz);
            list = objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

	/**
	 * json字符串转成Map
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 * @author 80002088
	 * 2016年9月1日
	 */
	public static Map<String, String> jsonToMap(String jsonStr) throws RuntimeException {
		JSONObject jsonObj = new JSONObject(jsonStr);
		Iterator<String> nameItr = jsonObj.keys();
		String name;
        Map<String, String> outMap = new HashMap<>();
		while (nameItr.hasNext()) {
			name = nameItr.next();
			outMap.put(name, jsonObj.getString(name));
		}
		return outMap;
	}
	
	
	/**
	 * 获取json中的对应key的值
	 * 先根据加引号的key至获取，没有获取到则key不加引号再获取一次
	 * @param json json字符串
	 * @param key json中的key值
	 * @return value
	 */
	public static String getJsonValueByKey(String json,String key){
		String value = findValueByKey(json, "\""+key+"\":");
		if(StringUtils.isEmpty(value)){
			return findValueByKey(json, key+":");
		}
		return value;
	}
	
	/**
	 * 描述：获取json中的对应key的值
	 * @param json json字符串
	 * @param key json中的key值
	 * @author 80002088
	 * 2016年10月18日
	 */
	private static String findValueByKey(String json, String key){
		String value="";
		int starIndex=json.indexOf(key);
		int endIndex=json.indexOf(",", starIndex);
		if(endIndex<0){
		    endIndex=json.indexOf("}", starIndex);
		}
		if(starIndex>-1&&endIndex>-1){
			String str=json.substring(starIndex, endIndex);//从key值处截取到第一个逗号
			value=str.split(":")[1].trim();//取冒号后面的值并去除前后空格
			if(value.startsWith("\"")){//去除引号
				value=value.substring(value.indexOf("\"")+1,value.lastIndexOf("\""));
			}
		}
		return value;
	}
}
