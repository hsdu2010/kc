package com.sf.shiva.oms.psm.service.eventmanager.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
                         
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.dto.deliverystatus.DeliveryStatusRequest;
import com.sf.shiva.oms.psm.common.enumtype.EventEnum;
import com.sf.shiva.oms.psm.service.eventmanager.EventManager;
                         
/**
 * 
 * 描述：派送事件处理类，获取事件代码
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月28日      01159741         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01159741
 * @since 1.0
 */
@Service
public class DeliveryEventManagerImpl implements EventManager<DeliveryStatusRequest> {
	/** SGS派件状态响应码与事件代码对应关系 */
	private static final Map<String, String> RESPONSECODE_MAPPING_EVENTCODE = new HashMap<>();
	static {
		RESPONSECODE_MAPPING_EVENTCODE.put(PackageStatusConstant.RESPONSE_CODE_41004, EventEnum.EVENT20_1000.getEventCode());// 到方改派件时间
		RESPONSECODE_MAPPING_EVENTCODE.put(PackageStatusConstant.RESPONSE_CODE_41005, EventEnum.EVENT20_1001.getEventCode());// 无法联系到派件客户
		RESPONSECODE_MAPPING_EVENTCODE.put(PackageStatusConstant.RESPONSE_CODE_41006, EventEnum.EVENT20_1002.getEventCode());// 到方周末不在
	}

	@Override 
	public String queryEventCode(DeliveryStatusRequest obj) {
		String responseCode = obj.getResponseCode();
		if (RESPONSECODE_MAPPING_EVENTCODE.containsKey(responseCode)) {
			return RESPONSECODE_MAPPING_EVENTCODE.get(responseCode);
		} else {// 其他(改派件员)
			return EventEnum.EVENT20_1003.getEventCode();
		}
	}

}
