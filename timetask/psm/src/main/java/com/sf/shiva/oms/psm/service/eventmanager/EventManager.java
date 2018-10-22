package com.sf.shiva.oms.psm.service.eventmanager;
/**
 * 
 * 描述：事件管理接口
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
public interface EventManager<T> {
	
	/**
	 * 获取事件编码
	 * @param obj
	 * @return 事件编码
	 * @author 01159741
	 * @date 2017年12月10日
	 */
	public String queryEventCode(T obj);
}
