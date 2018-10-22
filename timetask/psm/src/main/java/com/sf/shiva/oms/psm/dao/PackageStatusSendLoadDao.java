package com.sf.shiva.oms.psm.dao;

import java.util.List;

import com.sf.shiva.oms.psm.common.entity.PackageStatusSendLoadEntity;

/**
 * 描述：包裹状态重试发送Dao
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月10日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
public interface PackageStatusSendLoadDao {

    /** 
     * 保存下发加载记录
     * @param entity 包裹状态发送加载对象
     * @author 80002517
     * @date 2018年1月10日
     */
    void savePackageStatusSendLoad(PackageStatusSendLoadEntity entity);
    
    /** 
     * 删除下发加载记录
     * @param entity 包裹状态发送加载对象
     * @author 80002517
     * @date 2018年1月10日
     */
    void removePackageStatusSendLoad(PackageStatusSendLoadEntity entity);
    
    /** 
     * 查询包裹加载记录
     * @param querySize 查询条数
     * @return
     * @author 80002517
     * @date 2018年1月17日
     */
    List<PackageStatusSendLoadEntity> list(int querySize);
}
