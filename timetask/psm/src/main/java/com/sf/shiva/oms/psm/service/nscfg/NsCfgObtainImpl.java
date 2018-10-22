package com.sf.shiva.oms.psm.service.nscfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.csm.utils.INsCfgObtain;
import com.sf.shiva.oms.psm.datacache.NsTypeCodeCache;

/**
 * 
 * 描述：号段配置工具类，实现号段管理sdk包接口，用于获取号段代码对应的号段类型代码
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
public class NsCfgObtainImpl implements INsCfgObtain {
    
    @Autowired
    private NsTypeCodeCache nsTypeCodeCacheImpl;

    @Override
    public String getNsTypeCode(String nsCode) {
        return nsTypeCodeCacheImpl.getNsTypeCode(nsCode);
    }

}