package com.sf.shiva.oms.psm.service.eventmanager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.service.eventmanager.EventManager;
import com.sf.shiva.oms.psm.service.eventmanager.BarEventHandle;
/**
 * 
 * 描述：fvp事件管理实现类
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
public class FvpBarEventManagerImpl implements EventManager<FactRouteDto> {
	
	@Autowired
	private BarEventHandle specialBarEventHandleImpl;
	
	@Override
	public String queryEventCode(FactRouteDto obj) {
		return specialBarEventHandleImpl.queryEventCode(obj);
	}

}
