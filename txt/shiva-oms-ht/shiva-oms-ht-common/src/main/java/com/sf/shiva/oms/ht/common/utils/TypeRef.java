package com.sf.shiva.oms.ht.common.utils;

import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
/**
 * @description : 对象转换工具类
 * @author : 879149
 * @since : 1.0 HISTORY
 * 
 *        <pre>
 * ****************************************************************************
 *  ID   DATE            PERSON   REASON             DESC
 *  1    2016年1月28日              879149   Create/Modified     对象转换工具类
 * ****************************************************************************
 * </pre>
 */
public abstract class TypeRef<T> {
	private JavaType jt = TypeFactory.defaultInstance().constructType(
			((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

	/**
	 * 包内访问
	 * 
	 * @return
	 */
	JavaType getType() {
		return jt;
	}

}
