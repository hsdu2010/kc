package com.sf.shiva.oms.psm.datacache;

/**
 * 
 * 描述：事件与状态对应关系配置类缓存接口
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      01369610         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369610
 * @since 1.0
 */
@FunctionalInterface
public interface EventToStatusCache {

	/**
	 * 根据事件代码获取对应状态代码
	 * 
	 * @return 状态代码
	 * @author 01369610
	 * @date 2017年11月30日
	 */
	public String getStatusCodeByEventCode(String eventCode);
}
