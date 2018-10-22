package com.sf.shiva.oms.psm.datacache;

/**
 * 
 * 描述：操作码与事件对应关系缓存接口
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      01369610         Create
 * ****************************************************************************
 * </pre>
 * @author 01369610
 * @since 1.0
 */
@FunctionalInterface
public interface OpCodeToEventCache {
	/**
	 * 根据操作码获取对应事件代码
	 * 
	 * @return 事件代码
	 * @author 01369610
	 * @date 2017年11月30日
	 */
	public String getEventCodeByOpCode(String opCode);
}
