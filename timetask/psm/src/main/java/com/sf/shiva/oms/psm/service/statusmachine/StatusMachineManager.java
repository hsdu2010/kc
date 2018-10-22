package com.sf.shiva.oms.psm.service.statusmachine;

import java.util.List;

import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;

/**
 * 
 * 描述状态机接口
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
public interface StatusMachineManager {
    
	/**
	 * 事件的具体处理方法
	 * @param eventCode 事件编码
	 * @param currentStatus 当前状态
	 * @param objs 对象集合
	 * @return 返回包裹状态事件对象
	 * @author 01159741
	 * @date 2017年11月28日
	 */
	PackageStatusRecordEntity excute(String eventCode, String currentStatus, List<Object> objs);

}
