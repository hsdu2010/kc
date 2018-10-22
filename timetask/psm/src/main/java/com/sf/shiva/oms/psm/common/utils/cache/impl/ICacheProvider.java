package com.sf.shiva.oms.psm.common.utils.cache.impl;

/**
 * 描述：简单的缓存懒加载接口
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
public interface ICacheProvider<K, V> {
    /**
     * 根据key获取值
     * 
     * @param key
     *            比如用户名，角色代码等
     * @return key对应的对象，存入缓存
     */
    V getData(K key);
}
