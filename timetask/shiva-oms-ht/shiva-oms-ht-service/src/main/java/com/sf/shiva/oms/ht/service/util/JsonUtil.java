package com.sf.shiva.oms.ht.service.util;


import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper objectMapper = initObjectMapper();
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private JsonUtil(){
        
    }
    
    /**
     * 解码json串成对象
     * 
     * @param <T>
     * @param json
     * @param valueType
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T decode(String json, Class<?> valueType) {
        try {
            return (T) objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            logger.error("json parse decode error:{}", e);
        }
        return null;
    }
    
    /**
     * object --> json 
     * @param object 需要转换的对象
     * @return
     * String json字符串
     */
    public static String encode2json(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("json parse encode error:{}", e);
        }
        return null;
    }

    private static ObjectMapper initObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
        return objectMapper;
    }
}