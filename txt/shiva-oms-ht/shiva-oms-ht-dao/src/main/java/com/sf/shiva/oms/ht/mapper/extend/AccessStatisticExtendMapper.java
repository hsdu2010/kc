package com.sf.shiva.oms.ht.mapper.extend;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sf.shiva.oms.ht.domain.AccessStatistic;

/**
 * 
 * 描述：
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
public interface AccessStatisticExtendMapper {
    
    /**
     * 获取开始和结束时间范围内各个模块的访问统计情况
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author 01369626
     * @date 2018年6月12日
     */
    public List<AccessStatistic> findModuleAccessStatistics(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
    
    /**
     * 获取开始和结束时间范围内系统所有的访问统计情况
     * @param startTime
     * @param endTime
     * @return
     * @author 01369626
     * @date 2018年6月12日
     */
    public AccessStatistic findTotalAccessStatistic(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}
