package com.sf.shiva.oms.psm.service.datafilter.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.constants.FvpBarCodeStatusConstant;
import com.sf.shiva.oms.psm.datacache.FvpBarCodeCache;
import com.sf.shiva.oms.psm.service.datafilter.FvpBarDataFilterService;
import com.sf.shiva.oms.psm.service.datafilter.PackageNoCheckService;

/**
 * 
 * 描述：fvp巴枪数据过滤实现
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月13日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
@Service
public class FvpBarDataFilterServiceImpl implements FvpBarDataFilterService {

    private static final int FVP_DATA_STATUS_UNVALID = 2;

    @Autowired
    private FvpBarCodeCache fvpBarCodeCacheImpl;
    @Autowired
    private PackageNoCheckService packageNoCheckServiceImpl;

    /**
     * 对巴枪对象进行过滤，过滤规则如下：<br>
     * 1、数据转换失败或者运单号（MainWaybillNo）为空<br>
     * 2、巴枪status推断状态为2 3 5 6 7 8 9则过滤掉<br>
     * 3、巴枪Datastatus为2<br>
     * 4、巴枪OriginalRecord为false<br>
     * 5、巴枪码不存在定义的合法巴枪码集合中<br>
     * 6、操作时间<包裹操作时间(移动到状态机FvpBarPackageStatusServiceImpl中作判断)
     * 7、objTypeCode不为10(子单)或20(运单)则过滤
     * 8、包裹号为空或者包裹号长度不为12的也进行过滤
     * 
     * @param factRouteDto
     * @return true--数据合法，可以进行下一步操作;false--数据校验不通过，过滤数据
     * @see com.sf.shiva.oms.psm.service.datafilter.FvpBarDataFilterService#filterBar(com.sf.fvp.dto.FactRouteDto)
     * @author 01369626
     * @date 2017年12月13日
     */
    @Override
    public boolean filterBar(FactRouteDto factRouteDto) {
        if (null == factRouteDto || StringUtils.isEmpty(factRouteDto.getMainWaybillNo())) {
            return false;
        }
        if (!isFilterFvpBarByState(factRouteDto.getStatus()) || !packageNoCheckServiceImpl.validPackageNo(factRouteDto.getMainWaybillNo())) {
            return false;
        }
        return isFvpBarValid(factRouteDto);
    }

    /**
     * 根据推断状态判断是否需要过滤此巴枪码,如果推断状态不为0,1,4则过滤掉
     * 
     * @param state
     *            推断状态
     * @return true--不需要过滤;false--过滤数据
     * @author 01369626
     * @date 2017年12月13日
     */
    private static boolean isFilterFvpBarByState(int state) {
        return FvpBarCodeStatusConstant.getNormalStateMap().contains(state);
    }

    /**
     * 判断巴枪数据是否有效
     * 
     * @param factRouteDto
     *            巴枪数据
     * @return true--有效;false--无效
     * @author 01369626
     * @date 2017年12月13日
     */
    private boolean isFvpBarValid(FactRouteDto factRouteDto) {
        return fvpBarCodeCacheImpl.filteredBarCode(factRouteDto.getOpCode()) && factRouteDto.getDataStatus() != FVP_DATA_STATUS_UNVALID && factRouteDto.isOriginalRecord();
    }
    
}
