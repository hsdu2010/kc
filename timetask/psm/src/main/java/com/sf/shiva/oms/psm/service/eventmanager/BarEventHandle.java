package com.sf.shiva.oms.psm.service.eventmanager;

import com.sf.fvp.dto.FactRouteDto;
/**
 * 
 * 描述：获取时间接口
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
@FunctionalInterface
public interface BarEventHandle {
	
	/**
	 * 根据fvp信息获取事件编码
	 * @param factRouteDto fvp信息
	 * @return 事件编码
	 * @author 01159741
	 * @date 2017年11月28日
	 */
	String queryEventCode(FactRouteDto factRouteDto);
}
