package com.sf.shiva.oms.psm.datacache;

import com.sf.shiva.oms.psm.common.dto.DepartmentDto;

/**
 * 
 * 描述：机构信息缓存
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月4日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public interface DepartmentCache {
	/**
	 * 通过机构编码-网络网点编号获取机构及网络网点
	 * @param deptCode
	 * @return
	 * @author 01369521
	 * @date 2017年12月8日
	 */
	public DepartmentDto getCache(String deptCode);
}
