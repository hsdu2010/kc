package com.sf.shiva.oms.psm.service.eventmanager.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.datacache.OpCodeWhyCodeToEventCache;
import com.sf.shiva.oms.psm.service.eventmanager.BarEventHandle;
/**
 * 
 * 描述：操作码原因代码获取fvp事件编码
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
public class OpReasonCodeBarEventHandleImpl implements BarEventHandle {
	@Autowired
	private BarEventHandle opCodeBarEventHandleImpl;
	@Autowired
	private OpCodeWhyCodeToEventCache opCodeWhyCodeToEventCacheImpl;
	
	@Override
	public String queryEventCode(FactRouteDto factRouteDto) {
		String eventCode = queryOpReasonCodeEventCode(factRouteDto);
		if(StringUtils.isEmpty(eventCode)){
			eventCode = opCodeBarEventHandleImpl.queryEventCode(factRouteDto);
		}
		return eventCode;
	}

	/**
	 * 根据操作码、原因代码获取事件代码
	 * @param factRouteDto fvp信息
	 * @return 事件代码
	 * @author 01159741
	 * @date 2017年11月28日
	 */
	private String queryOpReasonCodeEventCode(FactRouteDto factRouteDto) {
		String reasonCode = StringUtils.isEmpty(factRouteDto.getStayWhyCode()) ? "" : factRouteDto.getStayWhyCode();
		return opCodeWhyCodeToEventCacheImpl.getEventCodeByOpReason(factRouteDto.getOpCode() + PackageStatusConstant.UNDER_LINE + reasonCode);
	}

}
