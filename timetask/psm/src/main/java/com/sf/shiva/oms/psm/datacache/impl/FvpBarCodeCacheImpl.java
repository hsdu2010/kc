package com.sf.shiva.oms.psm.datacache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.psm.cfgmanage.FvpBarCodeProperties;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.datacache.FvpBarCodeCache;

@Component
public class FvpBarCodeCacheImpl implements FvpBarCodeCache, InitializingBean {
	private Map<String, String> fvpBarCodeMap = new HashMap<>();
	@Autowired
	private FvpBarCodeProperties fvpBarCodeProperties;

	@Override
	public boolean filteredBarCode(String opCode) {
		return StringUtils.isNotEmpty(fvpBarCodeMap.get(opCode));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String fvpBarCodeDisConfJson = fvpBarCodeProperties.getFvpBarCodeConfig();
		if (StringUtils.isNotEmpty(fvpBarCodeDisConfJson)) {
			List<String> fvpBarCodeCfgList = ObjectMapperUtil.toObject(fvpBarCodeDisConfJson, List.class, String.class);
			fvpBarCodeCfgList.forEach(entity -> fvpBarCodeMap.put(entity, entity));
		}
	}
}
