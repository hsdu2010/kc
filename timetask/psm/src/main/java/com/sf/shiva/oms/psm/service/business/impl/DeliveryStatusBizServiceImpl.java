package com.sf.shiva.oms.psm.service.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.dto.deliverystatus.DeliveryStatusRequest;
import com.sf.shiva.oms.psm.common.dto.deliverystatus.Property;
import com.sf.shiva.oms.psm.common.dto.deliverystatus.UserDefList;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordExtendEntity;
import com.sf.shiva.oms.psm.common.enumtype.SystemResourceEnum;
import com.sf.shiva.oms.psm.common.utils.CollectionUtil;
import com.sf.shiva.oms.psm.common.utils.UUID22;
import com.sf.shiva.oms.psm.dao.PackageStatusDao;
import com.sf.shiva.oms.psm.service.business.BusinessService;

/**
 * 
 * 描述：包裹状态处理接口实现,能翻转SGS派件任务翻转状态、记录操作流水，不能翻转SGS派件任务记录操作流水
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
public class DeliveryStatusBizServiceImpl implements BusinessService {
    @Autowired
    private PackageStatusDao packageStatusDaoImpl;

    @Override
    public PackageStatusRecordEntity curStatusExcute(List<Object> objs, String currentStatus, String eventCode) {
        DeliveryStatusRequest deliveryStatus = (DeliveryStatusRequest) objs.get(0);// SGS派件状态接收dto
        PackageStatusRecordEntity recordEntity = buildPkgStatusRecordEntiy(deliveryStatus, currentStatus, eventCode);// 组装包裹状态事件记录流水
        packageStatusDaoImpl.savePackageStatusRecord(recordEntity, currentStatus, deliveryStatus.getWaybill().getMainNo(), recordEntity.getOperateTm());
        return recordEntity;
    }

    @Override
    public PackageStatusRecordEntity nextStatusExcute(List<Object> objs, String currentStatus, String nextStatus, String eventCode) {
        DeliveryStatusRequest deliveryStatus = (DeliveryStatusRequest) objs.get(0);// SGS派件状态接收dto
        PackageStatusEntity entity = buildPkgStatusEntity(objs.get(1), nextStatus, deliveryStatus);// 组装包裹状态对象
        PackageStatusRecordEntity recordEntity = buildPkgStatusRecordEntiy(deliveryStatus, nextStatus, eventCode);// 组装包裹状态事件记录流水
        packageStatusDaoImpl.saveSgsPkgStatusAndRecord(entity, recordEntity, deliveryStatus, nextStatus);
        return recordEntity;
    }

    /** 组装包裹状态对象PackageStatusEntity信息
     * @param obj 包裹状态对象
     * @param nextStatus 翻转状态
     * @param deliveryStatus SGS派件状态 
     * @return 包裹状态对象PackageStatusEntity
     * @author 01369610
     * @date 2017年12月21日
     */
    private static PackageStatusEntity buildPkgStatusEntity(Object obj, String nextStatus, DeliveryStatusRequest deliveryStatus) {
        PackageStatusEntity packageStatusEntity = (PackageStatusEntity) obj;// 包裹状态对象
        packageStatusEntity.setPackageStatus(nextStatus);
        packageStatusEntity.setOperateTm(new Date(deliveryStatus.getTimestamp()));
        packageStatusEntity.setModifyTm(new Date());
        return packageStatusEntity;
    }
    
    /** 组装包裹状态事件对象PackageStatusRecordEntity信息
     * @param deliveryStatus SGS派件状态 
     * @param packageStatus 包裹状态
     * @param eventCode 事件代码
     * @return 包裹状态事件对象PackageStatusRecordEntity
     * @author 01369610
     * @date 2017年12月14日
     */
    private static PackageStatusRecordEntity buildPkgStatusRecordEntiy(DeliveryStatusRequest deliveryStatus, String packageStatus, String eventCode) {
        PackageStatusRecordEntity entity = new PackageStatusRecordEntity();
        entity.setId(UUID22.getUUID22());
        entity.setPackageNo(deliveryStatus.getWaybill().getMainNo());// 包裹号
        entity.setPackageStatus(packageStatus);// 包裹状态
        entity.setEventCode(eventCode);// 事件代码
        entity.setOperateTm(new Date(deliveryStatus.getTimestamp()));// 操作时间
        entity.setOperateEmpCode(deliveryStatus.getCrouier());// 操作员工号
        entity.setOperateZoneCode(deliveryStatus.getDeptCode());// 操作网点代码
        entity.setResponseCode(deliveryStatus.getResponseCode());// SGS上报代码
        entity.setResponseType(deliveryStatus.getResponseType());// SGS上报类型
        entity.setResponseDescription(deliveryStatus.getDescription());// SGS上报描述
        assembleOpCodeAndReasonCode(deliveryStatus.getResponseCode(), entity);//设置巴枪操作码、异常码
        assembleExtendInfoList(deliveryStatus.getUserDefList(), entity);//设置扩展信息
        entity.setCreateTm(new Date());// 创建时间
        entity.setSysSource(SystemResourceEnum.SGS.getKey());//系统来源
        return entity;
    }
    
    /** 根据派件状态响应码设置巴枪操作码、异常码
     * @param responseCode 响应代码
     * @param entity 包裹状态事件记录对象
     * @author 01369610
     * @date 2018年1月9日
     */
    private static void assembleOpCodeAndReasonCode(String responseCode, PackageStatusRecordEntity entity) {
        if (PackageStatusConstant.getRespCodeToOpWhyCodeMap().containsKey(responseCode)) {
            String[] mappingArray = PackageStatusConstant.getRespCodeToOpWhyCodeMap().get(responseCode).split(PackageStatusConstant.UNDER_LINE);
            entity.setOperateCode(mappingArray[0]);
            entity.setOperateReasonCode(mappingArray[1]);
        }
    }
    
    /** 组装包裹状态事件扩展属性对象
     * @param UserDefList 派件状态扩展信息list
     * @param packageStatusRecordEntity 包裹状态事件记录对象
     * @author 01369610
     * @date 2017年12月20日
     */
    private static void assembleExtendInfoList(UserDefList userDefList, PackageStatusRecordEntity packageStatusRecordEntity) {
        if (userDefList != null && CollectionUtil.isNotEmpty(userDefList.getProperty())) {
            List<PackageStatusRecordExtendEntity> list = new ArrayList<>();
            PackageStatusRecordExtendEntity extendEntity;
            for (Property property : userDefList.getProperty()) {
                extendEntity = new PackageStatusRecordExtendEntity();
                extendEntity.setKey(property.getKey());
                extendEntity.setValue(property.getValue());
                list.add(extendEntity);
            }
            packageStatusRecordEntity.setExtendInfoList(list);// 扩展属性
        }
    }
}