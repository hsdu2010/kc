package com.sf.shiva.oms.ht.service.statistic;

import com.sf.shiva.oms.ht.domain.OperationInfo;

/**
 * 
 * 描述：统计服务service接口
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月5日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public interface StatisticService {

    /**
     * 保存操作信息
     * 
     * @param info
     *            操作信息
     * @author 01369626
     * @date 2018年6月5日
     */
    public void recordOperation(OperationInfo info);

}
