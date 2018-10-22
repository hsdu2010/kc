package com.sf.shiva.oms.psm.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

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
    private static final Logger logger = LoggerFactory.getLogger(ObjectMapperUtil.class);
    private static final ObjectMapper objectMapper;

    private ObjectMapperUtil() {
    }

    static {
        objectMapper = new ObjectMapper();
        // 【JSON序列化】JAVA对象转JSON时使用
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);// 禁用空对象转换json校验
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);// 去掉默认的时间戳格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS"));// 序列化时，日期的统一格式
        objectMapper.setSerializationInclusion(Include.NON_NULL);// 空值不序列化
        // 【JSON反序列化】JSON转JAVA对象时使用
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);// 反序列化时，属性不存在的兼容处理
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 忽略未知的字段
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);// 单引号处理
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);// 是否将允许使用非双引号属性名字-Map中KEY无引号处理
        // objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS,
        // true);//允许解析使用Java或C加加样式的注释

        // 自定义的序列化类
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new DateJsonDeserializer());// 字符串转Date对象自定义序列化类
        objectMapper.registerModule(module);
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
            logger.error("json to object by class error jsonStr:{}", json, e);
        }
        return null;
    }

    public static <T> T toObject(String json, TypeReference<T> type) {
        try {
            if (StringUtils.isNotEmpty(json)) {
                return objectMapper.readValue(json, type);
            }
        } catch (Exception e) {
            logger.error("json to object by typeReference error jsonStr:{}", json, e);
        }
        return null;
    }

    public static <T> T toObject(String json, Class<?> typeClass, Class<?>... beanClass) {
        try {
            if (StringUtils.isNotEmpty(json)) {
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(typeClass, beanClass));
            }
        } catch (Exception e) {
            logger.error("json to object error jsonStr:{}", json, e);
        }
        return null;
    }

    public static <T> T toObjectForMap(String json, Class<?> typeClass, Class<?> beanClass) {
        try {
            if (StringUtils.isNotEmpty(json)) {
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(typeClass, beanClass));
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
