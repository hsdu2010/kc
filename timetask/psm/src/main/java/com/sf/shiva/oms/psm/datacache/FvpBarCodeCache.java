package com.sf.shiva.oms.psm.datacache;

/**
 * 描述：巴枪码是否过滤缓存
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月11日      01369610         Create
 * ****************************************************************************
 * </pre>
 * @author 01369521
 * @since 1.0
 */
public interface FvpBarCodeCache {
	/**
	 * 当前巴枪码是否过滤
	 * @return
	 * @author 01369521
	 * @date 2017年12月12日
	 */
	boolean filteredBarCode(String opCode);
}
