package com.sf.shiva.oms.psm.common.utils.cache.impl.local;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.sf.shiva.oms.psm.common.utils.cache.CacheTaskScheduler;
import com.sf.shiva.oms.psm.common.utils.cache.ITaskSchedulerCache;
import com.sf.shiva.oms.psm.common.utils.cache.impl.IncrementLoadCacheProvider;


/**
 * 描述：增量加载缓存对象，定时加载
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
public class IncrementLoadCache<K, V> extends BaseGuavaCache<K, V> implements ITaskSchedulerCache {
	private static final Logger logger = LoggerFactory.getLogger(IncrementLoadCache.class);

	/** 是否在初始化的时候就同步的加载一次 */
	private boolean preLoad;

	/** 上次加载后，间隔多少秒重新加载 */
	private int refreshInterval = UNSET;

	/**
	 * 按照时间字符匹配 yyyy-MM-dd HH:mm， 不限制的时间部分，用*号代替，例如****-**-01 12:00
	 */
	private String refreshTimePattern = null;

	private CacheTaskScheduler cacheTask = null;

	protected IncrementLoadCacheProvider<K, V> cacheProvider;

	protected String cacheProviderBean;

	@SuppressWarnings("unchecked")
	@Override
	protected void initCache() {
		if (null == cacheProvider && StringUtils.isNotEmpty(cacheProviderBean)) {
			cacheProvider = super.getBean(cacheProviderBean, IncrementLoadCacheProvider.class);
			Assert.notNull(cacheProvider, "Can not find cache provider.");
		}
		if (null == cacheProvider) {
			logger.warn("Can not find cache provider {}.", getName());
		}
		super.setCache(buildCache());
		if (preLoad) {
			refreshCache();
		}
		if (null != cacheProvider) {
			this.cacheTask = createCacheTask();
			if (null != cacheTask && preLoad) {
				cacheTask.setLastRunTimeMillis(System.currentTimeMillis());
			}
		}
	}

	private void refreshCache() {
		if (null != cacheProvider) {
			logger.info("RefreshCache {} start.", super.getName());
			cacheProvider.incrementLoad(this);
			logger.info("RefreshCache {} end.", super.getName());
		}
	}

	private CacheTaskScheduler createCacheTask() {
		if (refreshInterval != UNSET) {
			return new CacheTaskScheduler(refreshInterval) {
				@Override
				public void run() {
					try {
						refreshCache();
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
						refreshCache();
					} catch (RuntimeException e) {
						logger.error("Refresh cache exception.", e);
					}
				}
			};
		}
		return null;
	}

	@Override
	public CacheTaskScheduler getCacheTask() {
		return cacheTask;
	}

	public void setPreLoad(boolean preLoad) {
		this.preLoad = preLoad;
	}

	public void setRefreshInterval(int refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

	public void setRefreshTimePattern(String refreshTimePattern) {
		this.refreshTimePattern = refreshTimePattern;
	}

	public void setCacheProvider(IncrementLoadCacheProvider<K, V> cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

	public void setCacheProviderBean(String cacheProviderBean) {
		this.cacheProviderBean = cacheProviderBean;
	}

	public IncrementLoadCacheProvider<K, V> getCacheProvider() {
		return cacheProvider;
	}

	public String getCacheProviderBean() {
		return cacheProviderBean;
	}
}
