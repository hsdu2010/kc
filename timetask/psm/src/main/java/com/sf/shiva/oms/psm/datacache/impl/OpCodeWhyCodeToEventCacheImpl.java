package com.sf.shiva.oms.psm.datacache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.psm.cfgmanage.OpCodeWhyCodeToEventProperties;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.entity.OpCodeWhyCodeToEventEntity;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.datacache.OpCodeWhyCodeToEventCache;

/**
 * 
 * 描述：操作码、原因代码与事件关系配置缓存接口实现类
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
public class OpCodeWhyCodeToEventCacheImpl implements OpCodeWhyCodeToEventCache, InitializingBean {
	@Autowired
	private OpCodeWhyCodeToEventProperties opCodeWhyCodeToEventProperties;

	private Map<String, String> opCodeWhyCodeToEventMap = new HashMap<>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		String opCodeWhyCodeToEventConfig = opCodeWhyCodeToEventProperties.getOpCodeWhyCodeToEventConfig();
		if (StringUtils.isNotEmpty(opCodeWhyCodeToEventConfig)) {
			List<OpCodeWhyCodeToEventEntity> opCodeWhyCodeToEventList = ObjectMapperUtil.toObject(opCodeWhyCodeToEventConfig, List.class, OpCodeWhyCodeToEventEntity.class);
			for (OpCodeWhyCodeToEventEntity opCodeWhyCodeToEvent : opCodeWhyCodeToEventList) {
				opCodeWhyCodeToEventMap.put(buildKey(opCodeWhyCodeToEvent), opCodeWhyCodeToEvent.getEventCode());
			}
		}
	}

	@Override
	public String getEventCodeByOpReason(String opCodeWhyCode) {
		return opCodeWhyCodeToEventMap.get(opCodeWhyCode);
	}

	/**
	 * 组装KYE：操作码_原因代码
	 * 
	 * @param cfg
	 *            操作码、原因代码与事件关系配置类
	 * @return key
	 * @author 01369610
	 * @date 2017年11月30日
	 */
	private String buildKey(OpCodeWhyCodeToEventEntity entity) {
		return new StringBuilder().append(entity.getOpCode()).append(PackageStatusConstant.UNDER_LINE)
				.append(StringUtils.isNotEmpty(entity.getReasonCode()) ? entity.getReasonCode() : "").toString();
	}
}
