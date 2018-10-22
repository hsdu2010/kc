package com.sf.shiva.oms.psm.datacache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.psm.cfgmanage.EventToLogicServiceProperties;
import com.sf.shiva.oms.psm.common.entity.EventToLogicServiceEntity;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.datacache.EventToBusinessServiceCache;
import com.sf.shiva.oms.psm.service.business.BusinessService;

/**
 * 
 * 描述：事件代码对应具体的包裹状态处理类配置缓存实现
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月6日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
@Component
public class EventToBusinessServiceCacheImpl implements EventToBusinessServiceCache, InitializingBean{
    
    private static final Logger logger = LoggerFactory.getLogger(EventToBusinessServiceCacheImpl.class);
	
	@Autowired
	private EventToLogicServiceProperties eventToLogicServiceProp;
	private Map<String, String> eventCodeToBeanIdMap = new HashMap<>();
	

	@Override
	public BusinessService getPkgStatusExecObjNameByCode(String eventCode) {
		String beanId = eventCodeToBeanIdMap.get(eventCode);
		try {
		    return SpringContext.getInstance().getBean(beanId, BusinessService.class);
		}catch (Exception e) {
		    logger.error("EventToBusinessServiceCacheImpl getPkgStatusExecObjNameByCode error.eventCode={},beanId={}", eventCode, beanId);
            throw e;
        }
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String eventToLogicServiceJson = eventToLogicServiceProp.getEventToLogicServiceConfig();
		if(StringUtils.isNotEmpty(eventToLogicServiceJson)) {
			List<EventToLogicServiceEntity> list = ObjectMapperUtil.toObject(eventToLogicServiceJson, List.class, EventToLogicServiceEntity.class);
			list.forEach(entity -> eventCodeToBeanIdMap.put(entity.getEventCode(), entity.getBeanId()));
		}
	}

}
