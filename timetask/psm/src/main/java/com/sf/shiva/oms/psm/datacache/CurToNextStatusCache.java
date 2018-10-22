package com.sf.shiva.oms.psm.datacache;

/**
 * 
 * 描述：状态翻转缓存
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      01369521         Create
 * ****************************************************************************
 * </pre>
 * @author 01369521
 * @since 1.0
 */
public interface CurToNextStatusCache {
	/**
	 * 当前状态能否翻转到下一状态
	 * @param currentStatus
	 * @param nextStatus
	 * @author 01369521
	 * @return 
	 * @date 2017年11月30日
	 */
	public boolean judgeNextStatus(String currentStatus,String nextStatus);
}
