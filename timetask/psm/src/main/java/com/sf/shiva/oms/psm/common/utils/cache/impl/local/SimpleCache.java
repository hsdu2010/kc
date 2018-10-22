package com.sf.shiva.oms.psm.common.utils.cache.impl.local;

/**
 * 描述：本地，自供给缓存，不需要Provider
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2014年10月16日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 */
public class SimpleCache<K, V> extends BaseGuavaCache<K, V> {

    @Override
    protected void initCache() {
        super.setCache(buildCache());
    }

}
