package com.sf.shiva.oms.psm.service.packagestatus.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.dto.deliverystatus.DeliveryStatusRequest;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.dao.PackageStatusDao;
import com.sf.shiva.oms.psm.service.eventmanager.EventManager;
import com.sf.shiva.oms.psm.service.packagestatus.AbstractPackageStatusService;
import com.sf.shiva.oms.psm.service.statusmachine.StatusMachineManager;

/**
 * 
 * 描述：SGS包裹状态接口实现 1.根据包裹号查询当前包裹状态PackageStatusEntity
 * 2.PackageStatusEntity不为null，且派件状态时间 >操作时间，则组装objs进入事件的具体处理方法
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月10日      01159741         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01159741
 * @since 1.0
 */
@Service
public class DeliveryPackageStatusServiceImpl extends AbstractPackageStatusService<DeliveryStatusRequest> {
    @Autowired
    private EventManager<DeliveryStatusRequest> deliveryEventManagerImpl;
    @Autowired
    private StatusMachineManager statusMachineManagerImpl;
    @Autowired
    private PackageStatusDao packageStatusDaoImpl;

    @Override
    protected String queryEventCode(DeliveryStatusRequest obj) {
        return deliveryEventManagerImpl.queryEventCode(obj);
    }

    @Override
    protected PackageStatusRecordEntity cal(DeliveryStatusRequest deliveryStatus, String eventCode) {
        PackageStatusEntity packageStatusEntity = packageStatusDaoImpl.getPackageStatusEntity(deliveryStatus.getWaybill().getMainNo());// 根据包裹号查询当前包裹状态
        if (isCanExecuteNext(deliveryStatus, packageStatusEntity)) {// 判断是否能进行下一步操作
            return statusMachineManagerImpl.excute(eventCode, packageStatusEntity.getPackageStatus(), buildObjs(deliveryStatus, packageStatusEntity));
        }
        return null;
    }

    /**
     * 组装objs参数对象
     * 
     * @param deliveryStatus
     *            SGS派件状态
     * @param packageStatusEntity
     *            包裹状态对象
     * @return objs参数对象
     * @author 01369610
     * @date 2018年1月8日
     */
    private static List<Object> buildObjs(DeliveryStatusRequest deliveryStatus, PackageStatusEntity packageStatusEntity) {
        List<Object> objs = new ArrayList<>(2);
        objs.add(deliveryStatus);
        objs.add(packageStatusEntity);
        return objs;
    }

    /**
     * 判断派件状态是否能进行下一步操作：派件状态时间 >操作时间
     * 
     * @param deliveryStatus
     *            SGS派件状态
     * @param packageStatusEntity
     *            包裹状态对象
     * @return true能，false不能
     * @author 01369610
     * @date 2017年12月13日
     */
    private static boolean isCanExecuteNext(DeliveryStatusRequest deliveryStatus, PackageStatusEntity packageStatusEntity) {
        return null != packageStatusEntity && deliveryStatus.getTimestamp() > packageStatusEntity.getOperateTm().getTime();
    }
}
