package com.sf.shiva.oms.psm.service.eventmanager.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.dto.DepartmentDto;
import com.sf.shiva.oms.psm.common.entity.PackageStatusEntity;
import com.sf.shiva.oms.psm.common.enumtype.EventEnum;
import com.sf.shiva.oms.psm.common.enumtype.StatusEnum;
import com.sf.shiva.oms.psm.dao.TableRowKeyDao;
import com.sf.shiva.oms.psm.dao.base.HBaseCommonDao;
import com.sf.shiva.oms.psm.datacache.DepartmentCache;
import com.sf.shiva.oms.psm.service.eventmanager.BarEventHandle;

/**
 * 
 * 描述：获取fvp特殊事件接口实现
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
public class SpecialBarEventHandleImpl implements BarEventHandle {

    @Autowired
    private BarEventHandle opReasonCodeBarEventHandleImpl;
    @Autowired
    private DepartmentCache departmentCacheImpl;
    @Autowired
    private TableRowKeyDao tableRowKeyDaoImpl;
    @Autowired
    private HBaseCommonDao hBaseCommonDaoImpl;

    @Override
    public String queryEventCode(FactRouteDto factRouteDto) {
        String eventCode = querySpecialEventCode(factRouteDto);
        if (StringUtils.isEmpty(eventCode)) {
            eventCode = opReasonCodeBarEventHandleImpl.queryEventCode(factRouteDto);
        }
        return eventCode;
    }

    /**
     * 根据fvp信息获取特殊事件代码
     * 
     * @param factRouteDto
     *            fvp信息
     * @return 事件代码
     * @author 01159741
     * @date 2017年11月28日
     */
    private String querySpecialEventCode(FactRouteDto factRouteDto) {
        if (PackageStatusConstant.OPCODE_31.equals(factRouteDto.getOpCode())) {
            return opCode31ToEventCode(factRouteDto.getZoneCode());
        } else if (PackageStatusConstant.OPCODE_70.equals(factRouteDto.getOpCode())) {
            return cancelExpressEventCode(factRouteDto);
        }else if (PackageStatusConstant.OPCODE_99.equals(factRouteDto.getOpCode())) {
            return remissionEventCode(factRouteDto);
        }
        return parcelSendingEventCode(factRouteDto.getMainWaybillNo(), factRouteDto.getOpCode());
    }

    /**
     * 获取31巴枪的事件代码
     * 
     * @param zoneCode
     *            机构代码
     * @return 事件代码
     * @author 01369626
     * @date 2017年12月15日
     */
    private String opCode31ToEventCode(String zoneCode) {
        DepartmentDto department = departmentCacheImpl.getCache(zoneCode);
        String typeCode = (null == department) ? "" : department.getTypeCode();
        if (PackageStatusConstant.GWB04.equals(typeCode)) { // typeCode为GWB04则返回海关卸车事件代码
            return EventEnum.EVENT10_1048.getEventCode();
        }
        return EventEnum.EVENT10_1047.getEventCode();
    }

    /**
     * 当前状态为准备派送，巴枪码为44或204时获取对应事件代码
     * 
     * @param waybillNo
     *            包裹号
     * @param opCode
     *            巴枪码
     * @return 当前状态为准备派送，巴枪码为44或204时返回10_1049，否则返回null
     * @author 01369626
     * @date 2017年12月15日
     */
    private String parcelSendingEventCode(String waybillNo, String opCode) {
        if (isSendingSpecialOpCode(opCode)) {
            PackageStatusEntity entity = hBaseCommonDaoImpl.get(tableRowKeyDaoImpl.getRowKey(waybillNo, PackageStatusEntity.class), PackageStatusEntity.class);
            if (null != entity && StatusEnum.STATUS40.getStatusCode().equals(entity.getPackageStatus())) {
                return EventEnum.EVENT10_1049.getEventCode();
            }
        }
        return null;
    }

    /**
     * 准备派送，判断是否特殊巴枪码--44和204
     * 
     * @param opCode
     * @return true--是;false--否
     * @author 01369626
     * @date 2017年12月15日
     */
    private static boolean isSendingSpecialOpCode(String opCode) {
        return PackageStatusConstant.OPCODE_44.equals(opCode) || PackageStatusConstant.OPCODE_204.equals(opCode);
    }

    /**
     * 判断异常原因码是否为46且extendAttach13为"sgs-exp-unite"，是则返回10_1057取消寄件事件代码，否则返回null
     * 
     * @param dto
     *            巴枪路由信息
     * @return 事件代码
     * @author 01369626
     * @date 2018年3月13日
     */
    private static String cancelExpressEventCode(FactRouteDto dto) {
        if (PackageStatusConstant.STAY_WHY_CODE_46.equals(dto.getStayWhyCode()) && PackageStatusConstant.CANCEL_EXPRESS_FLAG.equals(StringUtils.lowerCase(dto.getExtendAttach13()))) {
            return EventEnum.EVENT10_1057.getEventCode();
        }
        return null;
    }
    
    /**
     * 转寄退回事件代码获取
     * 	 扩展字段extendAttach13 = 1 时返回转寄事件代码
     *   扩展字段extendAttach13 = 2 时返回退回事件代码
     *   否则返回null
     * @param dto
     * @return 事件代码
     * @author 01377305
     * @date 2018年8月21日
     */
    private static String remissionEventCode(FactRouteDto dto) {
        if (PackageStatusConstant.FORWARD_FLAG.equals(StringUtils.lowerCase(dto.getExtendAttach4()))) {
            return EventEnum.EVENT10_1064.getEventCode();
        }else if (PackageStatusConstant.SENDBACK_FLAG.equals(StringUtils.lowerCase(dto.getExtendAttach4()))){
        	return EventEnum.EVENT10_1065.getEventCode();
        }
        return null;
    }
     
}
