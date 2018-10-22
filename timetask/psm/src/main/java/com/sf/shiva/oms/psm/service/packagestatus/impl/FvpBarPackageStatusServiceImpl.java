package com.sf.shiva.oms.psm.service.packagestatus.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.dao.PackageStatusDao;
import com.sf.shiva.oms.psm.service.eventmanager.EventManager;
import com.sf.shiva.oms.psm.service.packagestatus.AbstractPackageStatusService;
import com.sf.shiva.oms.psm.service.statusmachine.StatusMachineManager;

/**
 * 
 * 描述：fvp包裹状态接口实现
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
public class FvpBarPackageStatusServiceImpl extends AbstractPackageStatusService<FactRouteDto> {

    @Autowired
    private EventManager<FactRouteDto> fvpBarEventManagerImpl;
    @Autowired
    private StatusMachineManager statusMachineManagerImpl;
    @Autowired
    private PackageStatusDao packageStatusDaoImpl;

    @Override
    protected String queryEventCode(FactRouteDto obj) {
        return fvpBarEventManagerImpl.queryEventCode(obj);
    }

    @Override
    protected PackageStatusRecordEntity cal(FactRouteDto obj, String eventCode) {
        PackageStatusEntity packageStatusEntity = packageStatusDaoImpl.getPackageStatusEntity(obj.getMainWaybillNo());// 根据运单号获取当前状态信息
        if(validOperTm(obj, packageStatusEntity)) { //判断巴枪扫描时间是否不早于上次操作时间
            return statusMachineManagerImpl.excute(eventCode, getCurrentStatus(packageStatusEntity), buildObjs(obj, packageStatusEntity));
        }
        return null;
    }

    /**
     * 获取当前状态
     * 
     * @param packageStatusEntity
     *            当前状态信息
     * @return 当前状态
     * @author 01369626
     * @date 2018年1月8日
     */
    private static String getCurrentStatus(PackageStatusEntity packageStatusEntity) {
        return packageStatusEntity == null ? null : packageStatusEntity.getPackageStatus();
    }

    /**
     * 组装objs参数对象
     * 
     * @param dto
     *            FVP巴枪对象
     * @param packageStatusEntity
     *            包裹状态对象
     * @return objs参数对象
     * @author 01369626
     * @date 2018年1月8日
     */
    private static List<Object> buildObjs(FactRouteDto dto, PackageStatusEntity packageStatusEntity) {
        List<Object> objs = new ArrayList<>(2);
        objs.add(dto);
        objs.add(packageStatusEntity);
        return objs;
    }
    
    /**
     * 判断巴枪对象操作时间是否有效，若操作时间<包裹操作时间则无效
     * @param dto 巴枪路由对象
     * @param packageStatusEntity 包裹状态
     * @return true--有效;false--无效
     * @author 01369626
     * @date 2018年2月7日
     */
    private static boolean validOperTm(FactRouteDto dto, PackageStatusEntity packageStatusEntity) {
        return null == packageStatusEntity || dto.getBarScanTm().compareTo(packageStatusEntity.getOperateTm()) >= 0;
    }
}
