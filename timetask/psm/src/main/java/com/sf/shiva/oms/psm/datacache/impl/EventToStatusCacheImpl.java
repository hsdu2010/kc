package com.sf.shiva.oms.psm.datacache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.psm.cfgmanage.EventToStatusProperties;
import com.sf.shiva.oms.psm.common.entity.EventToStatusEntity;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.datacache.EventToStatusCache;

/**
 * 
 * 描述：事件与状态对应关系配置类缓存接口实现类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      01369610         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369610
 * @since 1.0
 */
@Component
public class EventToStatusCacheImpl implements EventToStatusCache, InitializingBean {
	@Autowired
	private EventToStatusProperties eventToStatusProperties;
	
	private Map<String, String> eventToStatusMap = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		String eventToStatusConfig = eventToStatusProperties.getEventToStatusConfig();
		if (StringUtils.isNotEmpty(eventToStatusConfig)) {
			List<EventToStatusEntity> eventToStatusList = ObjectMapperUtil.toObject(eventToStatusConfig, List.class, EventToStatusEntity.class);
			for (EventToStatusEntity eventToStatus : eventToStatusList) {
				eventToStatusMap.put(eventToStatus.getEventCode(), eventToStatus.getStatusCode());
			}
		}
	}
	
	@Override
	public String getStatusCodeByEventCode(String eventCode) {
		return eventToStatusMap.get(eventCode);
	}
}
