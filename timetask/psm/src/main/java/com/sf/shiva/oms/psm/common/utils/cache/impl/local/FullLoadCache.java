package com.sf.shiva.oms.psm.common.utils.cache.impl.local;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.cache.Cache;
import com.sf.shiva.oms.psm.common.exception.BusinessException;
import com.sf.shiva.oms.psm.common.utils.cache.CacheTaskScheduler;
import com.sf.shiva.oms.psm.common.utils.cache.ITaskSchedulerCache;
import com.sf.shiva.oms.psm.common.utils.cache.impl.IFullLoadCacheProvider;

/**
 * 描述：全量加载缓存，定时全部更新缓存
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
public class FullLoadCache<K, V> extends BaseGuavaCache<K, V> implements ITaskSchedulerCache {

    private static final Logger logger = LoggerFactory.getLogger(FullLoadCache.class);

    protected IFullLoadCacheProvider<K, V> cacheProvider;
    protected String cacheProviderBean;

    /** 是否在初始化的时候就同步的加载一次 */
    private boolean preLoad = true;

    /** 上次加载后，间隔多少秒重新加载 */
    private int refreshInterval = UNSET;

    /**
     * 按照时间字符匹配 yyyy-MM-dd HH:mm， 不限制的时间部分，用*号代替，例如****-**-01 12:00
     */
    private String refreshTimePattern = null;

    private CacheTaskScheduler cacheTask = null;

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(int refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public boolean isPreLoad() {
        return preLoad;
    }

    public void setPreLoad(boolean preLoad) {
        this.preLoad = preLoad;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initCache() {
        if (null == cacheProvider && StringUtils.isNotEmpty(cacheProviderBean)) {
            cacheProvider = super.getBean(cacheProviderBean, IFullLoadCacheProvider.class);
            Assert.notNull(cacheProvider, "Can not find cache provider.");
        }
        if (null == cacheProvider) {
            logger.warn("Can not find cache provider {}.", getName());
        }
        super.setCache(buildCache());
        if (preLoad) {
            refreshCache(false);
        }
        if (null != cacheProvider) {
            this.cacheTask = createCacheTask();
            if (null != cacheTask && preLoad) {
                cacheTask.setLastRunTimeMillis(System.currentTimeMillis());
            }
        }
    }

    private CacheTaskScheduler createCacheTask() {
        if (refreshInterval != UNSET) {
            return new CacheTaskScheduler(refreshInterval) {
                @Override
                public void run() {
                    try {
                        refreshCache(true);
                    } catch (RuntimeException e) {
                        logger.error("Refresh cache exception.", e);
                    }
                }
            };
        } else if (refreshTimePattern != null) {
            return new CacheTaskScheduler(refreshTimePattern) {
                @Override
                public void run() {
                    try {
                        refreshCache(true);
                    } catch (RuntimeException e) {
                        logger.error("Refresh cache exception.", e);
                    }
                }
            };
        }
        return null;
    }

    private void refreshCache(boolean clear) {
        if (null != cacheProvider) {
            logger.info("RefreshCache {} start.", super.getName());
            Map<? extends K, ? extends V> values = cacheProvider.reload();
            if (null == values) {
                values = Collections.emptyMap();
            }
            if (values.size() > getMaximumSize()) {
                throw new BusinessException("Exceed maximum size of the cache \"" + getName() + "\", actual : " + values.size() + ", maximum : " + getMaximumSize());
            }
            if (clear) {
                Cache<K, V> newCache = buildCache();
                newCache.putAll(values);
                super.setCache(newCache);
            } else {
                put(values);
            }
            logger.info("RefreshCache {} end.", super.getName());
        }
    }

    @Override
    public CacheTaskScheduler getCacheTask() {
        return cacheTask;
    }

    public void setRefreshTimePattern(String refreshTimePattern) {
        this.refreshTimePattern = refreshTimePattern;
    }

    public void setCacheProvider(IFullLoadCacheProvider<K, V> cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    public IFullLoadCacheProvider<K, V> getCacheProvider() {
        return cacheProvider;
    }

    public String getCacheProviderBean() {
        return cacheProviderBean;
    }

    public void setCacheProviderBean(String cacheProviderBean) {
        this.cacheProviderBean = cacheProviderBean;
    }
}
