package com.sf.shiva.oms.psm.common.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 描述：时间格式化工具类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月6日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class DateFormatUtil {

    private DateFormatUtil() {
        super();
    }

    private static final Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

    public static final String T_HHMM = "HHmm";
    public static final String T_HHMM_SPLIT = "HH:mm";
    public static final String T_HHMMSS = "HHmmss";
    public static final String T_HHMMSS_SPLIT = "HH:mm:ss";
    public static final String T_HHMMSSSSS = "HHmmssSSS";
    public static final String T_HHMMSSSSS_SPLIT = "HH:mm:ss,SSS";

    public static final String D_YYYYMMDD = "yyyyMMdd";
    public static final String D_YYYYMMDD_SPLIT = "yyyy-MM-dd";
    public static final String D_YYMMDD = "yyMMdd";
    public static final String D_YYMMDD_SPLIT = "yy-MM-dd";
    public static final String D_MMDD = "MMdd";
    public static final String D_MMDD_SPLIT = "MM-dd";

    /**
     * 格式化
     * 
     * @param date
     *            要格式化的日期时间对象
     * @param datePattern
     *            日期样式
     * @param timePattern
     *            时间样式
     * @param space
     *            是否用空格分隔日期与时间
     * @return 格式化结果
     * @author 568677
     * @date 2017年12月6日
     */
    public static String format(Date date, String datePattern, String timePattern, boolean space) {
        if (null == date || (StringUtils.isEmpty(datePattern) && StringUtils.isEmpty(timePattern))) {
            logger.error("param is null");
            return null;
        }
        return DateFormatUtils.format(date, getPattern(datePattern, timePattern, space));
    }

    /**
     * 将字符串转换为Date对象
     * 
     * @param dateStr
     *            字符串时间
     * @param datePattern
     *            日期样式
     * @param timePattern
     *            时间样式
     * @param space
     *            是否用空格分隔日期与时间
     * @return 格式化结果
     * @author 568677
     * @date 2018年1月5日
     */
    public static Date format(String dateStr, String datePattern, String timePattern, boolean space) {
        if (StringUtils.isEmpty(dateStr) || (StringUtils.isEmpty(datePattern) && StringUtils.isEmpty(timePattern))) {
            logger.error("param is null");
            return null;
        }
        Date date = null;
        try {
            date = DateUtils.parseDate(dateStr, getPattern(datePattern, timePattern, space));
        } catch (ParseException e) {
            logger.error("format to Date", e);
        }
        return date;
    }

    /**
     * 获取日期时间格式化样式
     * 
     * @param datePattern
     *            日期样式
     * @param timePattern
     *            时间样式
     * @param space
     *            是否用空格分隔日期与时间
     * @return 日期时间格式化样式
     * @author 568677
     * @date 2017年12月6日
     */
    private static String getPattern(String datePattern, String timePattern, boolean space) {
        StringBuilder pattern = new StringBuilder();
        if (StringUtils.isNotEmpty(datePattern)) {
            pattern.append(datePattern);
        }
        if (space) {
            pattern.append(" ");
        }
        if (StringUtils.isNotEmpty(timePattern)) {
            pattern.append(timePattern);
        }
        return pattern.toString();
    }
}
