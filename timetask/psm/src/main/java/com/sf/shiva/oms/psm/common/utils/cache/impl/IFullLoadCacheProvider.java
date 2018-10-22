package com.sf.shiva.oms.psm.common.utils.cache.impl;

import java.util.Map;

/**
 * 描述：全量加载接口
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
@FunctionalInterface
public interface IFullLoadCacheProvider<K, V> {

    /**
     * 一次性加载所有需要存入缓存的对象Map
     * 
     * @return 要存入缓存的对象Map
     */
    Map<? extends K, ? extends V> reload();
}
