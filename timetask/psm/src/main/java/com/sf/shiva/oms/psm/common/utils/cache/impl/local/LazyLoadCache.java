package com.sf.shiva.oms.psm.common.utils.cache.impl.local;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sf.shiva.oms.psm.common.exception.BusinessException;
import com.sf.shiva.oms.psm.common.utils.cache.impl.ICacheProvider;

/**
 * 描述：懒加载缓存，在访问时加载，然后存放到缓存
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
public class LazyLoadCache<K, V> extends BaseGuavaCache<K, V> {
	private static final Logger logger = LoggerFactory.getLogger(LazyLoadCache.class);

	protected ICacheProvider<K, V> cacheProvider;
	protected String cacheProviderBean;

	/**
	 * 由于懒加载可能去查找那些不存在的key的值，返回空会导致异常，
	 * 所以添加一个用于加载的cache，保证不会出现空值，当然这也也会把每次查询的key值都记录在缓存，
	 * 如果查询过多的不存在的key，会导致缓存被那些无值的key填满
	 */
	private Cache<K, Optional<V>> optionalValueCache;

	public ICacheProvider<K, V> getCacheProvider() {
		return cacheProvider;
	}

	public void setCacheProvider(ICacheProvider<K, V> cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

	public String getCacheProviderBean() {
		return cacheProviderBean;
	}

	public void setCacheProviderBean(String cacheProviderBean) {
		this.cacheProviderBean = cacheProviderBean;
	}

	@Override
	public void put(K key, V value) {
		optionalValueCache.put(key, Optional.fromNullable(value));
	}

	@Override
	public void put(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}
	}

	@Override
	public void remove(K key) {
		optionalValueCache.invalidate(key);
	}

	@Override
	public void remove(Collection<K> keys) {
		optionalValueCache.invalidateAll(keys);
	}

	@Override
	public void removeAll() {
		optionalValueCache.invalidateAll();
	}

	@Override
	public V get(K key) {
		try {
			Optional<V> value = ((LoadingCache<K, Optional<V>>) optionalValueCache).get(key);
			return null != value ? value.orNull() : null;
		} catch (ExecutionException e) {
			throw new BusinessException("Get cache single data exception from " + getName() + " for key : " + key, e);
		}
	}
	@Override
	public Map<K, V> get(Collection<K> keys) {
		try {
			return transform(((LoadingCache<K, Optional<V>>) optionalValueCache).getAll(keys));
		} catch (ExecutionException e) {
			throw new BusinessException("Get cache batch data exception from " + getName() + " for keys : " + keys, e);
		}
	}
	@Override
	public Map<K, V> getAll() {
		return Collections.unmodifiableMap(transform(optionalValueCache.asMap()));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void initCache() {
		if (null == cacheProvider && StringUtils.isNotEmpty(cacheProviderBean)) {
			cacheProvider = super.getBean(cacheProviderBean, ICacheProvider.class);
		}
		if (null == cacheProvider) {
			logger.warn("Can not find cache provider {}.", getName());
		}
		CacheLoader<K, Optional<V>> cacheLoader = new CacheLoader<K, Optional<V>>() {
			@Override
			public Optional<V> load(K key) throws Exception {
				return Optional.fromNullable(cacheProvider != null ? cacheProvider.getData(key) : null);
			}
		};
		this.optionalValueCache = buildCache(cacheLoader);
	}

	private Map<K, V> transform(Map<K, Optional<V>> values) {
		if (values == null || values.isEmpty()) {
			return Collections.emptyMap();
		}
		int size = values.size() / 2;
		Map<K, V> map = new HashMap<>(size == 0 ? 2 : size);
		for (Map.Entry<K, Optional<V>> e : values.entrySet()) {
			Optional<V> value = e.getValue();
			if (value.isPresent()) {
				map.put(e.getKey(), value.get());
			}
		}
		return map;
	}
}
