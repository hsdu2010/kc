package com.sf.shiva.oms.psm.service.packagestatus;

import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;

/**
 * 
 * 描述：包裹状态接口
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月10日      01159741         Create
 * ****************************************************************************
 * </pre>
 * @author 01159741
 * @since 1.0
 */
@FunctionalInterface
public interface PackageStatusService<T> {
	/**
	 * 包裹状态处理
	 * @param obj 接收需要处理的dto信息
	 * @return 包裹状态事件对象
	 * @author 01159741
	 * @date 2017年12月21日
	 */
	PackageStatusRecordEntity excute(T obj);
}
