package com.sf.shiva.oms.psm.service.eventmanager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.datacache.OpCodeToEventCache;
import com.sf.shiva.oms.psm.service.eventmanager.BarEventHandle;
/**
 * 
 * 描述：操作码获取fvp事件编码
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月28日      01159741         Create
 * ****************************************************************************
 * </pre>
 * @author 01159741
 * @since 1.0
 */
@Service
public class OpCodeBarEventHandleImpl implements BarEventHandle {
	
	@Autowired
	private OpCodeToEventCache opCodeToEventCacheImpl;

	@Override
	public String queryEventCode(FactRouteDto factRouteDto) {
		return opCodeToEventCacheImpl.getEventCodeByOpCode(factRouteDto.getOpCode());
	}

}
