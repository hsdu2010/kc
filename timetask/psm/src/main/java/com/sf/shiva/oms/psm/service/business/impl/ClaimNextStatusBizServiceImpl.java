package com.sf.shiva.oms.psm.service.business.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
import com.sf.shiva.oms.psm.common.entity.PackageSettleClaimsEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.enumtype.SystemResourceEnum;
import com.sf.shiva.oms.psm.common.utils.DateFormatUtil;
import com.sf.shiva.oms.psm.common.utils.UUID22;
import com.sf.shiva.oms.psm.dao.PackageStatusDao;
import com.sf.shiva.oms.psm.service.business.BusinessService;

/**
 * 
 * 描述：包裹状态处理接口处理方式实现，能翻转：理赔任务翻转状态和记录操作流水，不能翻转：SGS派件任务记录操作流水
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月28日      01369521         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369521
 * @since 1.0
 */
@Service
public class ClaimNextStatusBizServiceImpl implements BusinessService {
    @Autowired
    private PackageStatusDao packageStatusDaoImpl;
    private static final Logger logger = LoggerFactory.getLogger(ClaimNextStatusBizServiceImpl.class);

    @Override
    public PackageStatusRecordEntity nextStatusExcute(List<Object> objs, String currentStatus, String nextStatus, String eventCode) {
        CosClaimDto cosClaimDto = (CosClaimDto) objs.get(0);
        PackageStatusEntity packageStatusEntity = buildPackageStatusEntity(cosClaimDto, nextStatus, (PackageStatusEntity) objs.get(1));
        PackageStatusRecordEntity packageStatusRecordEntity = buildPackageStatusRecordEntity(cosClaimDto, nextStatus,eventCode);
        packageStatusDaoImpl.saveCosClaimPackageStatus(packageStatusEntity, packageStatusRecordEntity, cosClaimDto, nextStatus);
        packageStatusDaoImpl.savePackageSettleClaim(cosClaimDto, buildPackageSettleClaimsEntity(cosClaimDto));
        return packageStatusRecordEntity;
    }

    @Override
    public PackageStatusRecordEntity curStatusExcute(List<Object> objs, String currentStatus, String eventCode) {
        logger.error("ClaimNextStatusBizServiceImpl curStatusExcute err: cos is access forbbiden  this method");
        return null;
    }

    /**
     * 组建理赔信息存入hbase包裹状态表的实体
     * 
     * @param cosClaimDto 理赔dto
     * @return 包裹理赔信息PackageSettleClaimsEntity
     * @author 01369521
     * @date 2018年1月9日
     */
    private static PackageSettleClaimsEntity buildPackageSettleClaimsEntity(CosClaimDto cosClaimDto) {
        PackageSettleClaimsEntity packageSettleClaimsEntity = new PackageSettleClaimsEntity();
        packageSettleClaimsEntity.setCreateTm(new Date());
        packageSettleClaimsEntity.setPackageNo(cosClaimDto.getCarryId());
        packageSettleClaimsEntity.setState(cosClaimDto.getClaimStatus());
        return packageSettleClaimsEntity;
    }

    /**
     * 构建包裹状态表entity 取原有包裹状态记录的创建时间
     * @param cosClaimDto 理赔dto
     * @param nextStatus  翻转状态
     * @param oldPackageStatusEntity 之前查出的包裹状态记录，以供构建新包裹状态记录
     * @return 包裹状态对象
     * @author 01369521
     * @date 2018年1月10日
     */
    private static PackageStatusEntity buildPackageStatusEntity(CosClaimDto cosClaimDto, String nextStatus, PackageStatusEntity oldPackageStatusEntity) {
        PackageStatusEntity packageStatusEntity = new PackageStatusEntity();
        packageStatusEntity.setId(UUID22.getUUID22());
        packageStatusEntity.setPackageNo(cosClaimDto.getCarryId());
        packageStatusEntity.setPackageStatus(nextStatus);
        packageStatusEntity.setModifyTm(new Date());
        packageStatusEntity.setCreateTm(oldPackageStatusEntity.getCreateTm());
        packageStatusEntity.setOperateTm(DateFormatUtil.format(cosClaimDto.getClaimProcessTimeString(), DateFormatUtil.D_YYYYMMDD_SPLIT, DateFormatUtil.T_HHMMSS_SPLIT, true));
        return packageStatusEntity;
    }

    /** 构建包裹状态事件记录表entity
     * @param cosClaimDto 理赔dto
     * @param nextStatus 翻转状态
     * @param eventCode 事件代码
     * @return 包裹状态事件对象
     * @author 01369521
     * @date 2017年12月15日
     */
    private static PackageStatusRecordEntity buildPackageStatusRecordEntity(CosClaimDto cosClaimDto, String nextStatus,String eventCode) {
        PackageStatusRecordEntity packageStatusRecordEntity = new PackageStatusRecordEntity();
        packageStatusRecordEntity.setId(UUID22.getUUID22());
        packageStatusRecordEntity.setPackageNo(cosClaimDto.getCarryId());
        packageStatusRecordEntity.setPackageStatus(nextStatus);
        packageStatusRecordEntity.setEventCode(eventCode);
        packageStatusRecordEntity.setOperateTm(DateFormatUtil.format(cosClaimDto.getClaimProcessTimeString(), DateFormatUtil.D_YYYYMMDD_SPLIT, DateFormatUtil.T_HHMMSS_SPLIT, true));
        packageStatusRecordEntity.setOperateEmpCode(cosClaimDto.getClaimProcessUserName());
        packageStatusRecordEntity.setOperateZoneCode(cosClaimDto.getClaimProcessNetCode());
        packageStatusRecordEntity.setOperateReasonCode(SystemResourceEnum.COS.getKey());
        packageStatusRecordEntity.setSysSource(SystemResourceEnum.COS.getKey());
        packageStatusRecordEntity.setCreateTm(new Date());
        return packageStatusRecordEntity;
    }

}