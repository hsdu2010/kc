package com.sf.shiva.oms.psm.datacache.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sf.shiva.oms.psm.cfgmanage.CurToNextStatusProperties;
import com.sf.shiva.oms.psm.common.entity.CurToNextStatusEntity;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.datacache.CurToNextStatusCache;

/**
 * 
 * 描述：状态翻转缓存
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      01369521         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369521
 * @since 1.0
 */
@Component
public class CurToNextStatusCacheImpl implements CurToNextStatusCache, InitializingBean {

	@Autowired
	private CurToNextStatusProperties curToNextStatusProperties;
	private Map<String, Set<String>> curToNextStatusMap = new HashMap<>();

	/**
	 * 当前状态能否翻转到下一状态
	 * 
	 * @param currentStatus
	 * @param nextStatus
	 * @author 01369521
	 * @date 2017年11月30日
	 */
	@Override
	public boolean judgeNextStatus(String currentStatus, String nextStatus) {
		Set<String> ableChangeStatusSet = StringUtils.isEmpty(currentStatus) ? curToNextStatusMap.get("null") : curToNextStatusMap.get(currentStatus);
		return CollectionUtils.isNotEmpty(ableChangeStatusSet) ? ableChangeStatusSet.contains(nextStatus) : false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String curToNextStatusDisConfJson = curToNextStatusProperties.getCurToNextStatusConfig();
		if (StringUtils.isNotEmpty(curToNextStatusDisConfJson)) {
			List<CurToNextStatusEntity> curToNextStatusList = ObjectMapperUtil.toObject(curToNextStatusDisConfJson, new TypeReference<List<CurToNextStatusEntity>>() {
			});
			curToNextStatusList.forEach(entity -> curToNextStatusMap.put(entity.getCurStatus(), new HashSet<>(entity.getNextStatusList())));
		}
	}
}
