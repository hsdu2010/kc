package com.sf.shiva.oms.psm.common.utils.cache;

import java.util.Collection;
import java.util.Map;

/**
 * 缓存接口，key --&gt; value 形式
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
public interface ICache<K, V> {

    /**
     * 获取缓存注册名称
     * 
     * @return 缓存注册名称
     */
    String getName();

    /**
     * 获取指定key的缓存
     * 
     * @param key
     *            需要获取的缓存key值
     * @return key值对应的缓存对象，不存在返回null
     */
    V get(K key);

    /**
     * 根据指定的key集合，从缓存中获取值，组成map对象<br>
     * 如果key在缓存中不存在，则map中不包含此key<br>
     * 返回的map对象改动（如果可以）不影响缓存的改动
     * 
     * @param keys
     *            指定的key集合
     * @return key以及其值组成的Map对象
     */
    Map<K, V> get(Collection<K> keys);

    /**
     * 获取缓存中所有存在的key以及其值组成的Map对象<br>
     * 返回的map对象改动（如果可以）不影响缓存的改动
     * 
     * @return key以及其值组成的Map对象
     */
    Map<K, V> getAll();

    /**
     * 设置缓存对象
     * 
     * @param key
     *            缓存key
     * @param value
     *            缓存值
     */
    void put(K key, V value);

    /**
     * 设置缓存对象集
     * 
     * @param m
     *            缓存key和值组成的map
     */
    void put(Map<? extends K, ? extends V> m);

    /**
     * 删除一个key值
     * 
     * @param key
     *            要删除的Key
     */
    void remove(K key);

    /**
     * 批量删除keys
     * 
     * @param keys
     *            要删除的Key列表
     */
    void remove(Collection<K> keys);

    /**
     * 删除所有缓存
     */
    void removeAll();
}
