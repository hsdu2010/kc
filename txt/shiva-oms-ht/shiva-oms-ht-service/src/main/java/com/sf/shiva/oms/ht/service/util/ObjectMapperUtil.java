package com.sf.shiva.oms.ht.service.util;

/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 描述：Json转换工具类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2016年6月24日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author zhangYao 568677
 * @since 1.0
 */
public class ObjectMapperUtil {
    private static Logger logger = LoggerFactory.getLogger(ObjectMapperUtil.class);
    private static final ObjectMapper objectMapper;
    private ObjectMapperUtil(){}
    static {
        objectMapper = new ObjectMapper();
        // 序列化时使用
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);// 禁用空对象转换json校验
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);// 去掉默认的时间戳格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));// 序列化时，日期的统一格式
        // objectMapper.setSerializationInclusion(Include.NON_NULL);// 空值不序列化
        // 反序列化时使用
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);// 反序列化时，属性不存在的兼容处理
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 忽略未知的字段
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);// 单引号处理
    }

    /**
     * 
     * 将Json字符串转换为对象
     * 
     * @param json
     *            字符串
     * @param clazz
     *            对象类型
     * @return 转换后的对象
     * @throws IOException
     * @author zhangYao 568677
     * @date 2016年6月24日
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            if (StringUtils.isNotEmpty(json)) {
                return objectMapper.readValue(json, clazz);
            }
        } catch (Exception e) {
            logger.error("json to object error jsonStr:{}", json, e);
        }
        return null;
    }

    /**
     * 将对象转换为Json
     * 
     * @param entity
     *            实体对象
     * @return json字符串
     * @author zhangYao 568677
     * @date 2016年6月24日
     */
    public static <T> String toJson(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (Exception e) {
            logger.error("object to json error", e);
        }
        return null;
    }
}
