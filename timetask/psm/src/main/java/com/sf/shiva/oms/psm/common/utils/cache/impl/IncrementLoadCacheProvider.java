package com.sf.shiva.oms.psm.common.utils.cache.impl;

import com.sf.shiva.oms.psm.common.utils.cache.ICache;

/**
 * 描述：增量加载接口，定时任务会定时回调此接口，实现并配置好此接口，则可以实现定时更新缓存
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2014年10月30日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 */
@FunctionalInterface
public interface IncrementLoadCacheProvider<K, V> {

    /**
     * 增量加载回调接口
     * 
     * @param cache
     *            缓存对象
     */
    void incrementLoad(ICache<K, V> cache);
}
