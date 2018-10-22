package com.sf.shiva.oms.psm.common.utils.cache.impl.local;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.sf.shiva.oms.psm.common.utils.cache.BaseCache;

/**
 * 描述：本地guava缓存基类
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2014年10月16日      568677         Create
 * ****************************************************************************
 * </pre>
 * @author 568677
 */
public abstract class BaseGuavaCache<K, V> extends BaseCache<K, V> {
    protected static final int UNSET = -1;
    
    protected volatile Cache<K, V> cache;

    /**
     * 必须指定最大容量，超出容量的时候，不常使用的会清除出缓存，
     * 如果是全量更新缓存，加载的数据超出容量时会报错
     */
    private long maximumSize = 5000;

    /** 访问后多少秒过期，-1表示不过期 */
    private int expiredAfterAccess = UNSET;

    /** 写入后多少秒过期，-1表示不过期 */
    private int expiredAfterWrite = UNSET;
    
    protected abstract void initCache();
    @Override
    public void afterPropertiesSet() throws Exception {
        initCache();
        super.afterPropertiesSet();
    }

    public long getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(long maximumSize) {
        this.maximumSize = maximumSize;
    }
    
    public int getExpiredAfterAccess() {
        return expiredAfterAccess;
    }

    public void setExpiredAfterAccess(int expiredAfterAccess) {
        this.expiredAfterAccess = expiredAfterAccess;
    }

    public int getExpiredAfterWrite() {
        return expiredAfterWrite;
    }

    public void setExpiredAfterWrite(int expiredAfterWrite) {
        this.expiredAfterWrite = expiredAfterWrite;
    }
    
    public Cache<K, V> getCache() {
        return cache;
    }
    
    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public void put(Map<? extends K, ? extends V> m) {
        cache.putAll(m);
    }

    @Override
    public void remove(K key) {
        cache.invalidate(key);
    }

    @Override
    public void remove(Collection<K> keys) {
        cache.invalidateAll(keys);
    }

    @Override
    public void removeAll() {
        cache.invalidateAll();
    }

    @Override
    public V get(K key) {
        return cache.getIfPresent(key);
    }
    @Override
    public Map<K, V> get(Collection<K> keys) {
        return cache.getAllPresent(keys);
    }
    @Override
    public Map<K, V> getAll() {
        return Collections.unmodifiableMap(cache.asMap());
    }

    protected <E, F> Cache<E, F> buildCache() {
        return buildCache(null);
    }

    protected <E, F> Cache<E, F> buildCache(CacheLoader<E, F> cacheLoader) {
        CacheBuilder<Object, Object> build = CacheBuilder.newBuilder();
        if (UNSET != getExpiredAfterAccess()) {
            build.expireAfterAccess(getExpiredAfterAccess(), TimeUnit.SECONDS);
        }
        if (UNSET != getExpiredAfterWrite()) {
            build.expireAfterWrite(getExpiredAfterWrite(), TimeUnit.SECONDS);
        }
        build.maximumSize(getMaximumSize());
        return null == cacheLoader ? build.build() : build.build(cacheLoader);
    }

    public void setCache(Cache<K, V> cache) {
        this.cache = cache;
    }
}
