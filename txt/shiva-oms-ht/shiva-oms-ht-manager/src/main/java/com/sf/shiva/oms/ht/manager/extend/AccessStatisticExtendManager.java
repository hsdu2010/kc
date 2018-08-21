package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.AccessStatistic;

/**
 * 
 * 描述：访问统计扩展manager
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
public interface AccessStatisticExtendManager {
    
    /**
     * 获取各个模块的访问统计记录
     * @return
     * @author 01369626
     * @date 2018年6月12日
     */
    public List<AccessStatistic> findAccessStatistics();
    
    
}
