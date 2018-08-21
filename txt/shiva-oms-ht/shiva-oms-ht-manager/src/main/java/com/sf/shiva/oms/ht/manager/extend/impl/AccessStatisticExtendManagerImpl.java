package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.ht.domain.AccessStatistic;
import com.sf.shiva.oms.ht.manager.extend.AccessStatisticExtendManager;
import com.sf.shiva.oms.ht.mapper.extend.AccessStatisticExtendMapper;

/**
 * 
 * 描述：访问统计扩展manager实现
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月12日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@Component
public class AccessStatisticExtendManagerImpl implements AccessStatisticExtendManager{
    
    @Autowired
    private AccessStatisticExtendMapper accessStatisticExtendMapper;

    @Override
    public List<AccessStatistic> findAccessStatistics() {
        Calendar calendar = getLastDay();
        Date startTime = getStartTime(calendar);
        Date endTime = getEndTime(calendar);
        List<AccessStatistic> list = accessStatisticExtendMapper.findModuleAccessStatistics(startTime, endTime);
        AccessStatistic statistic = accessStatisticExtendMapper.findTotalAccessStatistic(startTime, endTime);
        if(statistic != null && statistic.getUsageCount() != null) {
            list.add(accessStatisticExtendMapper.findTotalAccessStatistic(startTime, endTime));
        }
        return list;
    }
    
    /**
     * 获取前一天的日期
     * @return
     * @author 01369626
     * @date 2018年6月12日
     */
    private static Calendar getLastDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return calendar;
    }
    
    /**
     * 获取开始时间，为指定日期的00:00:00
     * @param calendar
     * @return
     * @author 01369626
     * @date 2018年6月12日
     */
    private static Date getStartTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * 获取结束时间，为指定日期的23:59:59
     * @param calendar
     * @return
     * @author 01369626
     * @date 2018年6月12日
     */
    private static Date getEndTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    
}
