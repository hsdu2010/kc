package com.sf.shiva.oms.psm.datacache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.psm.common.dto.DepartmentDto;
import com.sf.shiva.oms.psm.common.utils.cache.ICache;
import com.sf.shiva.oms.psm.datacache.DepartmentCache;

/**
 * 
 * 描述：机构信息缓存实现类
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
@Component
public class DepartmentCacheImpl implements DepartmentCache {
	@Autowired
	private ICache<String, DepartmentDto> departmentCacheProvider;

	/**
	 * 通过机构编码-网络网点编号获取机构及网络网点
	 * 
	 * @param deptCode
	 *            机构代码
	 * @return DepartmentDto 机构代码信息dto
	 * @see com.sf.shiva.oms.psm.datacache.DepartmentCache#getCache(java.lang.String)
	 * @author 01369521
	 * @date 2017年12月8日
	 */
	@Override
	public DepartmentDto getCache(String deptCode) {
		DepartmentDto departmentDto = departmentCacheProvider.get(deptCode);
		if (null != departmentDto) {
			return departmentDto;
		} else {
			departmentCacheProvider.remove(deptCode);
			return null;
		}
	}

}
