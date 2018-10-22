package com.sf.shiva.oms.psm.datacache.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sf.shiva.oms.psm.common.utils.cache.ICache;
import com.sf.shiva.oms.psm.datacache.NsTypeCodeCache;

/**
 * 
 * 描述：号段类型代码缓存实现
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年10月15日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@Repository
public class NsTypeCodeCacheImpl implements NsTypeCodeCache{
    
    @Autowired
    private ICache<String, String> nsTypeCodeCacheProvider;

    @Override
    public String getNsTypeCode(String nsCode) {
        String nsTypeCode = nsTypeCodeCacheProvider.get(nsCode);
        if(StringUtils.isEmpty(nsTypeCode)) {
            nsTypeCodeCacheProvider.remove(nsCode);
        }
        return nsTypeCode;
    }

}
