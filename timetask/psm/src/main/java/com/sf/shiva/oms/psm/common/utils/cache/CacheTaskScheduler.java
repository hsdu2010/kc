package com.sf.shiva.oms.psm.common.utils.cache;

import java.util.Date;
import java.util.regex.Pattern;

import com.sf.shiva.oms.psm.common.utils.DateFormatUtil;

/**
 * 描述：定时调度任务对象，提供多种调度模式
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2014年10月30日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 */
public abstract class CacheTaskScheduler implements Runnable {

    /**
     * 调度模式枚举
     */
    private enum SCHEDULE_MODE {
        INTERVAL, TIME_PATTERN
    }

    private final SCHEDULE_MODE mode;

    private long intervalMillis = -1L;
    private long lastRunTimeMillis = -1L;

    private Pattern timePattern = null;

    /**
     * 按照时间字符匹配 yyyy-MM-dd HH:mm， 不限制的时间部分，用*号代替，例如****-**-01 12:00
     * 
     * @param timePattern
     *            时间模板
     */
    public CacheTaskScheduler(String timePattern) {
        timePattern = timePattern.replace("*", "\\d");
        this.timePattern = Pattern.compile(timePattern);
        this.intervalMillis = 60000;
        mode = SCHEDULE_MODE.TIME_PATTERN;
    }

    /**
     * 间隔多少秒
     * 
     * @param interval
     *            间隔秒
     */
    public CacheTaskScheduler(int interval) {
        this.intervalMillis = interval * 1000L;
        mode = SCHEDULE_MODE.INTERVAL;
    }

    /**
     * @param timeMillis
     *            判断时间
     * @return 指定时间是否是需要运行时段
     */
    public boolean isTimeForSchedule(long timeMillis) {
        switch (mode) {
        case INTERVAL:
            return isTimeForScheduleInterval(timeMillis);
        case TIME_PATTERN:
            return isTimeForScheduleTimePattern(timeMillis);
        default:
            return false;
        }
    }

    private boolean isTimeForScheduleInterval(long timeMillis) {
        if (intervalMillis == -1) {
            return false;
        }
        if (timeMillis - lastRunTimeMillis > intervalMillis) {
            lastRunTimeMillis = timeMillis;
            return true;
        }
        return false;
    }

    private boolean isTimeForScheduleTimePattern(long timeMillis) {
        if (null == timePattern) {
            return false;
        }
        if (timeMillis - lastRunTimeMillis > intervalMillis && timePattern.matcher(DateFormatUtil.format(new Date(timeMillis), DateFormatUtil.D_YYYYMMDD_SPLIT, DateFormatUtil.T_HHMM_SPLIT, true)).matches()) {
            lastRunTimeMillis = timeMillis;
            return true;
        }
        return false;
    }

    public long getLastRunTimeMillis() {
        return lastRunTimeMillis;
    }

    public void setLastRunTimeMillis(long lastRunTimeMillis) {
        this.lastRunTimeMillis = lastRunTimeMillis;
    }
}
