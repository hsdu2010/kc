package com.sf.shiva.oms.psm.service.eventmanager.impl;

import org.springframework.stereotype.Service;

import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
import com.sf.shiva.oms.psm.common.enumtype.EventEnum;
import com.sf.shiva.oms.psm.common.enumtype.StatusEnum;
import com.sf.shiva.oms.psm.service.eventmanager.EventManager;


/**
 * 
 * 描述：理赔事件处理类
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
public class ClaimEventManagerImpl implements EventManager<CosClaimDto>{
	
	/**
	 * 获取事件编码
	 * @param obj 接收的对象
	 * @return 事件编码
	 * @author 01369521
	 * @date 2017年12月1日
	 */
	@Override
	public String queryEventCode(CosClaimDto obj) {
		return StatusEnum.STATUS3.getStatusCode().equals(obj.getClaimStatus())?EventEnum.EVENT30_1000.getEventCode():"";
	}

}
