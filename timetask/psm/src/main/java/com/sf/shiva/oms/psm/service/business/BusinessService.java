package com.sf.shiva.oms.psm.service.business;

import java.util.List;

import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
/**
 * 
 * 描述：包裹状态处理接口
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月29日      01159741         Create
 * ****************************************************************************
 * </pre>
 * @author 01159741
 * @since 1.0
 */
public interface BusinessService {

	/** 不翻转状态时包裹状态信息处理
	 * @param objs 对象信息集合
	 * @param currentStatus 当前状态
	 * @param eventCode 事件码
	 * @return 包裹状态事件对象packageStatusEventEntity
	 * @author 01369610
	 * @date 2017年12月21日
	 */
	PackageStatusRecordEntity curStatusExcute(List<Object> objs, String currentStatus, String eventCode);
	
	/** 翻转状态时包裹状态信息处理
	 * @param objs 对象信息集合
	 * @param currentStatus 当前状态
	 * @param nextStatus 下个状态
	 * @param eventCode 事件码
	 * @return 包裹状态事件对象packageStatusEventEntity
	 * @author 01369610
	 * @date 2017年12月21日
	 */
	PackageStatusRecordEntity nextStatusExcute(List<Object> objs, String currentStatus, String nextStatus, String eventCode);
}
