package com.sf.shiva.oms.psm.common.utils;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 
 * 描述：ObjectMapper中字符串时间反序列化为Date对象时，自定义序列化类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月12日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {

    /**
     * 支持DateFormat格式：<br>
     * yyyy-MM-dd HH:mm:ss,SSS<br>
     * yyyy-MM-dd HH:mm:ss<br>
     * yyyy-MM-dd
     * 
     * @return 日期对象
     * @author 568677
     * @date 2018年1月12日
     */

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String dateStr = jp.getText();
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        } else if (dateStr.length() == 23) {
            return DateFormatUtil.format(dateStr, DateFormatUtil.D_YYYYMMDD_SPLIT, DateFormatUtil.T_HHMMSSSSS_SPLIT, true);
        } else if (dateStr.length() == 19) {
            return DateFormatUtil.format(dateStr, DateFormatUtil.D_YYYYMMDD_SPLIT, DateFormatUtil.T_HHMMSS_SPLIT, true);
        } else if (dateStr.length() == 10) {
            return DateFormatUtil.format(dateStr, DateFormatUtil.D_YYYYMMDD_SPLIT, null, false);
        } else {
            throw new IOException(dateStr + " str to java.util.Date error please check dateFormat");
        }
    }

}
