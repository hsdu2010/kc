package com.sf.shiva.oms.psm.service.business.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.enumtype.ExtInfoEnum;
import com.sf.shiva.oms.psm.common.enumtype.StatusEnum;
import com.sf.shiva.oms.psm.common.enumtype.SystemResourceEnum;
import com.sf.shiva.oms.psm.common.utils.PackageStatusExtEntityUtil;
import com.sf.shiva.oms.psm.common.utils.UUID22;
import com.sf.shiva.oms.psm.dao.PackageStatusDao;
import com.sf.shiva.oms.psm.service.business.BusinessService;

/**
 * 
 * 描述：包裹状态处理接口第已种处理方式实现
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
public class FvpBarStatusBizServiceImpl implements BusinessService {

    @Autowired
    private PackageStatusDao packageStatusDaoImpl;

    /**
     * 不翻转状态时，巴枪对象数据处理:<br/>
     * 1、获取巴枪轨迹对象<br/>
     * 2、组装包裹流水信息<br/>
     * 3、保存扩展信息<br/>
     * 4、保存包裹流水记录
     * 
     * @param objs
     *            对象信息集合
     * @param currentStatus
     *            包裹当前状态
     * @param eventCode
     *            事件代码
     * @return 包裹流水记录
     * @see com.sf.shiva.oms.packagestatus.cal.service.business.BusinessService#curStatusExcute(java.util.List,
     *      java.lang.String, java.lang.String)
     * @author 01369626
     * @date 2018年1月10日
     */
    @Override
    public PackageStatusRecordEntity curStatusExcute(List<Object> objs, String currentStatus, String eventCode) {
        FactRouteDto factRouteDto = (FactRouteDto) objs.get(0); // 获取巴枪轨迹对象
        PackageStatusRecordEntity entity = fillPackageStatusBaseEvent(factRouteDto, currentStatus, eventCode); // 组装流水基本信息
        curStatusSaveExtendEntity(entity, factRouteDto, currentStatus);// 保存扩展信息
        packageStatusDaoImpl.savePackageStatusRecord(entity, currentStatus, factRouteDto.getMainWaybillNo(), factRouteDto.getBarScanTm());
        return entity;
    }

    @Override
    public PackageStatusRecordEntity nextStatusExcute(List<Object> objs, String currentStatus, String nextStatus, String eventCode) {
        FactRouteDto factRouteDto = (FactRouteDto) objs.get(0);// 获取巴枪轨迹对象
        PackageStatusEntity packageStatusEntity = buildPackageStatus((PackageStatusEntity) objs.get(1), factRouteDto, nextStatus);// 获取包裹状态对象
        PackageStatusRecordEntity packageStatusRecordEntity = buildPackageStatusRecord(factRouteDto, nextStatus, eventCode);// 获取包裹状态流水对象
        packageStatusDaoImpl.saveFvpBarPackageStatusAndRecord(packageStatusEntity, packageStatusRecordEntity, factRouteDto, nextStatus);
        return packageStatusRecordEntity;
    }

    /**
     * 翻转包裹状态时，填充包裹操作流水
     * 
     * @param factRouteDto
     *            巴枪数据
     * @param status
     *            状态
     * @param eventCode
     *            事件代码
     * @return 包裹操作流水
     * @author 01369626
     * @date 2017年12月25日
     */
    private static PackageStatusRecordEntity buildPackageStatusRecord(FactRouteDto factRouteDto, String status, String eventCode) {
        PackageStatusRecordEntity entity = fillPackageStatusBaseEvent(factRouteDto, status, eventCode);
        saveCourierCode(entity, factRouteDto.getCourierCode(), status); // 当包裹状态为准备派送时，填充收派员工号
        saveHandoverInfo(entity, factRouteDto); // 巴枪码为204时(快件交接)保存扩展信息
        saveOperateProperty(entity, factRouteDto); // 巴枪码为80时(派送成功时)保存相应信息
        saveWaybillProperty(entity, factRouteDto); // 巴枪码为70 异常码为34时候 设置附加信息
        saveFcSendingProperty(entity, factRouteDto); // 巴枪码为125时保存扩展信息(柜机编码、收件人手机号、丰巢柜地址、用户取件码、收派员取件码)
        saveCollectProperty(entity, factRouteDto); // 巴枪码为50时保存扩展字段
        saveTouchPointProperty(entity, factRouteDto); // 巴枪码为664时保存扩展字段
        saveConvenientStoreProperty(entity, factRouteDto); // 巴枪码为130时保存扩展字段
        saveWaybillForwardProperty(entity, factRouteDto);// 巴枪码为626/627时，保存转寄信息
        return entity;
    }

    /**
     * 填充包裹操作流水基本信息
     * 
     * @param factRouteDto
     *            巴枪数据
     * @param status
     *            当前状态
     * @param eventCode
     *            事件代码
     * @return 包裹操作流水
     * @author 01369626
     * @date 2017年12月16日
     */
    private static PackageStatusRecordEntity fillPackageStatusBaseEvent(FactRouteDto factRouteDto, String status, String eventCode) {
        PackageStatusRecordEntity entity = new PackageStatusRecordEntity();
        entity.setId(UUID22.getUUID22());
        entity.setPackageNo(factRouteDto.getMainWaybillNo());
        entity.setPackageStatus(status);
        entity.setEventCode(eventCode);
        entity.setOperateCode(factRouteDto.getOpCode());
        entity.setOperateReasonCode(factRouteDto.getStayWhyCode());
        entity.setOperateTm(factRouteDto.getBarScanTm());
        entity.setOperateEmpCode(factRouteDto.getBarOprCode());
        entity.setOperateZoneCode(factRouteDto.getZoneCode());
        entity.setCreateTm(new Date());
        entity.setSysSource(SystemResourceEnum.FVP.getKey());
        return entity;
    }

    /**
     * 不翻转状态时，保存扩展字段
     * 
     * @param entity
     *            包裹流水记录
     * @param factRouteDto
     *            巴枪轨迹对象
     * @param currentStatus
     *            当前状态
     * @author 01369626
     * @date 2018年1月9日
     */
    private static void curStatusSaveExtendEntity(PackageStatusRecordEntity entity, FactRouteDto factRouteDto, String currentStatus) {
        saveCourierCode(entity, factRouteDto.getCourierCode(), currentStatus);
        saveHandoverInfo(entity, factRouteDto); // 巴枪码为204时(快件交接)保存扩展信息
        saveOperateProperty(entity, factRouteDto); // 巴枪码为80时(派送成功时)保存相应信息
        saveCollectProperty(entity, factRouteDto); // 巴枪码为50时保存扩展字段
        saveNewWaybillNoProperty(entity, factRouteDto);// 巴枪码为648时，保存关联新单号信息
    }

    /**
     * 组装包裹状态实体对象
     * 
     * @param entity
     *            包裹状态对象
     * @param factRouteDto
     *            巴枪数据
     * @param status
     *            包裹状态
     * @return 包裹状态对象
     * @author 01369626
     * @date 2017年12月25日
     */
    private static PackageStatusEntity buildPackageStatus(PackageStatusEntity entity, FactRouteDto factRouteDto, String status) {
        PackageStatusEntity resultEntity = new PackageStatusEntity();
        if (null == entity) {
            resultEntity.setId(UUID22.getUUID22());
            resultEntity.setPackageNo(factRouteDto.getMainWaybillNo());
            resultEntity.setCreateTm(new Date());
        } else {
            resultEntity.setId(entity.getId());
            resultEntity.setPackageNo(entity.getPackageNo());
            resultEntity.setCreateTm(entity.getCreateTm());
        }
        resultEntity.setPackageStatus(status);
        resultEntity.setModifyTm(new Date());
        resultEntity.setOperateTm(factRouteDto.getBarScanTm());
        return resultEntity;
    }

    /**
     * 当包裹状态为准备派送时，填充收派员工号
     * 
     * @param entity
     *            包裹操作流水对象
     * @param courierCode
     *            收派员工号
     * @param status
     *            包裹状态
     * @author 01369626
     * @date 2017年12月25日
     */
    private static void saveCourierCode(PackageStatusRecordEntity entity, String courierCode, String status) {
        if (StatusEnum.STATUS40.getStatusCode().equals(status)) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.COURIER_CODE.getName(), courierCode);
        }
    }

    /**
     * 当巴枪码为204时，保存快件交接扩展信息字段
     * 
     * @param entity
     *            包裹状态流水
     * @param dto
     *            巴枪对象
     * @author 01369626
     * @date 2018年6月26日
     */
    private static void saveHandoverInfo(PackageStatusRecordEntity entity, FactRouteDto dto) {
        if (PackageStatusConstant.OPCODE_204.equals(dto.getOpCode())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.SIGNED_PASS_WORD.getName(), dto.getExtendAttach1());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.IDENTITY_VERIFY_FLAG.getName(), dto.getExtendAttach2());
        }
    }

    /**
     * 巴枪码为70 异常码为34时候 设置附加信息
     * 
     * @param entity
     *            包裹操作流水信息
     * @param factRouteDto
     *            巴枪数据
     * @author 01369626
     * @date 2017年12月25日
     */
    private static void saveWaybillProperty(PackageStatusRecordEntity entity, FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_70.equals(factRouteDto.getOpCode()) && PackageStatusConstant.STAY_WHY_CODE_34.equals(factRouteDto.getStayWhyCode())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.NEW_WAYBILL_NO.getName(), factRouteDto.getExtendAttach1());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.WAYBILL_NO.getName(), factRouteDto.getWaybillNo());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.CONSIGNOR_NAME.getName(), factRouteDto.getExtendAttach10());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.CONSIGNOR_PHONE.getName(), factRouteDto.getExtendAttach11());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.CONSIGNEE_PHONE.getName(), factRouteDto.getExtendAttach12());
        }
    }

    /**
     * 巴枪码为80时(派送成功时)保存相应信息
     * V1.8 新增便利店编码扩展字段
     * 
     * @param entity
     *            包裹流水实体
     * @param factRouteDto
     *            巴枪数据
     * @author 01369626
     * @date 2017年12月25日
     */
    private static void saveOperateProperty(PackageStatusRecordEntity entity, FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_80.equals(factRouteDto.getOpCode())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.COURIER_CODE.getName(), factRouteDto.getCourierCode());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.PHONE.getName(), factRouteDto.getPhone());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.PHONE_ZONE.getName(), factRouteDto.getPhoneZone());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ALTERD_CONSIGNEE_NAME.getName(), factRouteDto.getExtendAttach10());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ALTERD_CONSIGNEE_PHONE.getName(), factRouteDto.getExtendAttach11());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ELECTRONIC_SIGN_FLAG.getName(), factRouteDto.getExtendAttach8());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ALLOGRAPH_ADDRESS.getName(), factRouteDto.getExtendAttach16());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.COST_TYPE.getName(), factRouteDto.getExtendAttach12());// 费用类型
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.PAYMENT_MODE.getName(), factRouteDto.getExtendAttach13());// 付款方式
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.MONTHLY_STATEMENT_NUMBER.getName(), factRouteDto.getExtendAttach14());// 月结卡号
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ADMIN_CONFIRM_FLAG.getName(), factRouteDto.getExtendAttach18());// 管理员确认标记
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ALTER_CONVENIENTSTORE_FLAG.getName(), factRouteDto.getAccountantCode());
        }
    }

    /**
     * 巴枪码为125时保存扩展信息(柜机编码、收件人手机号、丰巢柜地址、用户取件码、收派员取件码)
     * 
     * @param entity
     *            包裹流水实体
     * @param factRouteDto
     *            巴枪数据
     * @author 01369626
     * @date 2018年1月3日
     */
    private static void saveFcSendingProperty(PackageStatusRecordEntity entity, FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_125.equals(factRouteDto.getOpCode())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.FC_CABINET_NO.getName(), factRouteDto.getExtendAttach5());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.FC_CABINET_ADDR.getName(), factRouteDto.getExtendAttach6());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.FC_CONSUMER_PICK_UP_NO.getName(), factRouteDto.getExtendAttach7());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.FC_DELIVER_PICK_UP_NO.getName(), factRouteDto.getExtendAttach8());
        }
    }

    /**
     * 巴枪码为50时，保存扩展信息(改付标记)
     * V1.8 新增便利店编码扩展字段
     * 
     * @param entity
     *            包裹流水实体
     * @param factRouteDto
     *            巴枪数据
     * @author 01369626
     * @date 2018年5月21日
     */
    private static void saveCollectProperty(PackageStatusRecordEntity entity, FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_50.equals(factRouteDto.getOpCode())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ALTER_PAYMENT_FLAG.getName(), factRouteDto.getExtendAttach41());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ALTER_CONVENIENTSTORE_FLAG.getName(), factRouteDto.getAccountantCode());
        }
    }

    /**
     * 巴枪码为130时，保存扩展信息(便利店交接)
     * 
     * @param entity
     * @param factRouteDto
     */
    private static void saveConvenientStoreProperty(PackageStatusRecordEntity entity, FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_130.equals(factRouteDto.getOpCode()) && StringUtils.isNotEmpty(factRouteDto.getAccountantCode())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ALTER_CONVENIENTSTORE_FLAG.getName(), factRouteDto.getAccountantCode());
        }
    }

    /**
     * 巴枪码为664时，保存扩展信息(客户接触点交接)
     * 
     * @param entity
     * @param factRouteDto
     */
    private static void saveTouchPointProperty(PackageStatusRecordEntity entity, FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_664.equals(factRouteDto.getOpCode()) && StringUtils.isNotEmpty(factRouteDto.getExtendAttach3())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.ALTER_TOUCHPOINT_FLAG.getName(), factRouteDto.getExtendAttach3());
        }
    }

    /**
     * 巴枪码为648时，扩展字段保存新单号
     * 
     * @param entity
     *            包裹状态流水记录
     * @param factRouteDto
     *            巴枪操作码
     * @author 01369626
     * @date 2018年9月6日
     */
    private static void saveNewWaybillNoProperty(PackageStatusRecordEntity entity, FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_648.equals(factRouteDto.getOpCode())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.NEW_WAYBILL.getName(), factRouteDto.getExtendAttach3());
        }
    }

    /**
     * 巴枪码为626/627时，保存转寄第三方公司名称和单号
     * 
     * @param entity
     *            包裹状态流水记录
     * @author 01369626
     * @date 2018年9月6日
     */
    private static void saveWaybillForwardProperty(PackageStatusRecordEntity entity, FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_626.equals(factRouteDto.getOpCode()) || PackageStatusConstant.OPCODE_627.equals(factRouteDto.getOpCode())) {
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.FORWARD_COMPANY_NAME.getName(), factRouteDto.getExtendAttach3());
            PackageStatusExtEntityUtil.fillExtendEntity(entity, ExtInfoEnum.FORWARD_WAYBILL_NO.getName(), factRouteDto.getExtendAttach4());
        }
    }

}