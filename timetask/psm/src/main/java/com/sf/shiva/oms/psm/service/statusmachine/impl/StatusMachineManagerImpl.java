package com.sf.shiva.oms.psm.service.statusmachine.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.enumtype.EventEnum;
import com.sf.shiva.oms.psm.datacache.CurToNextStatusCache;
import com.sf.shiva.oms.psm.datacache.EventToStatusCache;
import com.sf.shiva.oms.psm.datacache.impl.EventToBusinessServiceCacheImpl;
import com.sf.shiva.oms.psm.service.statusmachine.StatusMachineManager;

/**
 * 
 * 描述状态机接口实现
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月28日      01159741         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01159741
 * @since 1.0
 */
@Service
public class StatusMachineManagerImpl implements StatusMachineManager {
    @Autowired
    private CurToNextStatusCache curToNextStatusCacheImpl;
    @Autowired
    private EventToStatusCache eventToStatusCacheImpl;
    @Autowired
    private EventToBusinessServiceCacheImpl eventToBusinessServiceCacheImpl;

    /**
     * 是否可翻转
     * 
     * @param currentStatus
     *            当前状态
     * @param nextStatus
     *            下个状态
     * @return true:可翻转；false不可翻转
     * @author 01159741
     * @date 2017年11月28日
     */
    private boolean ableChangeStatus(String currentStatus, String nextStatus) {
        return curToNextStatusCacheImpl.judgeNextStatus(currentStatus, nextStatus);
    }

    /**
     * 根据事件编码获取状态码
     * 
     * @param eventCode
     *            事件编码
     * @return 状态码
     * @author 01159741
     * @date 2017年11月28日
     */
    private String queryStatusByEventCode(String eventCode) {
        return eventToStatusCacheImpl.getStatusCodeByEventCode(eventCode);
    }
    /**
     * 1、可翻转状态时，执行翻转状态逻辑<br/>
     * 2、不可翻转状态时，若事件无需关注状态，且需要保存流水时，执行保存流水逻辑<br/>
     * 3、不可翻转状态时，若事件需要根据状态判断是否保存流水，判断事件对应状态与包裹当前状态相同时才下发流水：
     * e.g. 包裹状态为中转运输中(20)时，接收到50巴枪操作，此时下发流水无业务意义，所以需要对状态进行判断<br/>
     * 4、不可翻转状态，且不需要下发流水时，直接结束
     * @param eventCode
     * @param currentStatus
     * @param objs
     * @return
     * @see com.sf.shiva.oms.psm.service.statusmachine.StatusMachineManager#excute(java.lang.String, java.lang.String, java.util.List)
     * @author 01369626
     * @date 2018年5月3日
     */
    @Override
    public PackageStatusRecordEntity excute(String eventCode, String currentStatus, List<Object> objs) {
        String nextStatus = queryStatusByEventCode(eventCode);// 根据事件代码获取翻转状态
        if (ableChangeStatus(currentStatus, nextStatus)) { // 可翻转
            return eventToBusinessServiceCacheImpl.getPkgStatusExecObjNameByCode(eventCode).nextStatusExcute(objs, currentStatus, nextStatus, eventCode);
        } else if ((EventEnum.getNeedSaveRecordEventCodeSet().contains(eventCode) && StringUtils.isNotEmpty(currentStatus)) || needSaveRecordForSameStatus(eventCode, currentStatus, nextStatus)) { // 不可翻转，且需要保存流水
            return eventToBusinessServiceCacheImpl.getPkgStatusExecObjNameByCode(eventCode).curStatusExcute(objs, currentStatus, eventCode);
        }
        return null;
    }

    /**
     * 根据包裹状态和事件代码，判断是否需要保存下发流水
     * 
     * @param eventCode
     *            事件代码
     * @param currentStatus
     *            当前状态
     * @param nextStatus
     *            翻转状态
     * @return 当前状态=翻转状态且事件代码为需要下发流水的事件代码时，返回true
     * @author 01369626
     * @date 2018年4月17日
     */
    private static boolean needSaveRecordForSameStatus(String eventCode, String currentStatus, String nextStatus) {
        return EventEnum.getNeedSaveRecordWithStatusEvents().contains(eventCode) && StringUtils.equals(currentStatus, nextStatus);
    }

}
