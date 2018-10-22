package com.sf.shiva.oms.psm.datacache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.psm.cfgmanage.OpCodeToEventProperties;
import com.sf.shiva.oms.psm.common.entity.OpCodeToEventEntity;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.datacache.OpCodeToEventCache;

/**
 * 
 * 描述：操作码与事件对应关系缓存接口实现类
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
public class OpCodeToEventCacheImpl implements OpCodeToEventCache, InitializingBean {
	@Autowired
	private OpCodeToEventProperties opCodeToEventProperties;

	private Map<String, String> opCodeToEventMap = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		String opCodeToEventConfig = opCodeToEventProperties.getOpCodeToEventConfig();
		if (StringUtils.isNotEmpty(opCodeToEventConfig)) {
			List<OpCodeToEventEntity> opCodeToEventList = ObjectMapperUtil.toObject(opCodeToEventConfig, List.class, OpCodeToEventEntity.class);
			for (OpCodeToEventEntity opCodeToEvent : opCodeToEventList) {
				opCodeToEventMap.put(opCodeToEvent.getOpCode(), opCodeToEvent.getEventCode());
			}
		}
	}
	
	@Override
	public String getEventCodeByOpCode(String opCode) {
		return opCodeToEventMap.get(opCode);
	}
}
