package com.sf.shiva.oms.psm.service.datafilter.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.csm.utils.INsCfgObtain;
import com.sf.shiva.oms.csm.utils.WaybillNoUtils;
import com.sf.shiva.oms.csm.utils.biz.NsCfgCache;
import com.sf.shiva.oms.psm.service.datafilter.PackageNoCheckService;

/**
 * 
 * 描述：包裹号校验服务类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年10月15日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
@Service
public class PackageNoCheckServiceImpl implements PackageNoCheckService, InitializingBean {
    
    @Autowired
    private INsCfgObtain nsCfgObtainImpl;

    @Override
    public boolean validPackageNo(String packageNo) {
        return WaybillNoUtils.isWaybillNo(packageNo, true);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        NsCfgCache.initNsCfgObtain(nsCfgObtainImpl);//需要显式调用sdk包中NsCfgCache的initNsCfgObtain，设置获取号段类型代码的实现类
    }

}
