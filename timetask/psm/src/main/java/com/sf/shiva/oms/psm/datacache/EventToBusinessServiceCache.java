package com.sf.shiva.oms.psm.datacache;

import com.sf.shiva.oms.psm.service.business.BusinessService;

/**
 * 
 * 描述：事件代码对应具体的包裹状态处理类配置缓存接口
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月6日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public interface EventToBusinessServiceCache {
	
	/**
	 * 根据事件编码获取
	 * @return 包裹状态处理对象
	 * @author 01369626
	 * @date 2017年12月6日
	 */
	public BusinessService getPkgStatusExecObjNameByCode(String eventCode);
	
}
