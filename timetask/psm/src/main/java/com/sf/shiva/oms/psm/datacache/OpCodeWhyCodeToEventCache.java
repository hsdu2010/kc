package com.sf.shiva.oms.psm.datacache;

/**
 * 
 * 描述：操作码、原因代码与事件关系配置缓存接口
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
public interface OpCodeWhyCodeToEventCache {
	/**
	 * 根据操作码_原因代码获取对应事件代码
	 * @param OpCodeWhyCode 操作码_原因代码
	 * @return 事件代码
	 * @author 01369610
	 * @date 2017年11月30日
	 */
	public String getEventCodeByOpReason(String OpCodeWhyCode);
}
