package com.sf.shiva.oms.psm.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sf.shiva.oms.psm.common.entity.PackageStatusSendLoadEntity;
import com.sf.shiva.oms.psm.dao.PackageStatusSendLoadDao;
import com.sf.shiva.oms.psm.dao.TableRowKeyDao;
import com.sf.shiva.oms.psm.dao.base.HBaseCommonDao;

/**
 * 描述：包裹状态重试发送Dao 实现类
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
@Repository
public class PackageStatusSendLoadDaoImpl implements PackageStatusSendLoadDao{

    @Autowired
    private HBaseCommonDao commonDao;
    @Autowired
    private TableRowKeyDao tableRowKeyDao;
    
    @Override
    public void savePackageStatusSendLoad(PackageStatusSendLoadEntity entity) {
        commonDao.saveAny(tableRowKeyDao.getRowKey(entity.getPackageNo(), entity.getCreateTm(), PackageStatusSendLoadEntity.class), entity);
    }

    @Override
    public void removePackageStatusSendLoad(PackageStatusSendLoadEntity entity) {
        commonDao.removeAny(tableRowKeyDao.getRowKey(entity.getPackageNo(), entity.getCreateTm(), PackageStatusSendLoadEntity.class), entity);
    }

    @Override
    public List<PackageStatusSendLoadEntity> list(int querySize) {
        return commonDao.scan(PackageStatusSendLoadEntity.class, querySize);
    }

}
