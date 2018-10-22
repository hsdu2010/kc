package com.sf.shiva.oms.psm.service.datasend;


/**
 * 描述：包裹状态发送
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月12日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
public interface PackageStatusSendService {
    
    /** 
     * 发送包裹状态
     * @param param 包裹状态流水
     * @author 80002517
     * @date 2017年12月12日
     */
    void sendPackageStatus(Object param);
    
    /** 
     * 重新发送包裹状态
     * @param param 包裹状态重试
     * @author 80002517
     * @date 2017年12月12日
     */
    void reSendPackageStatus(Object param);
}
