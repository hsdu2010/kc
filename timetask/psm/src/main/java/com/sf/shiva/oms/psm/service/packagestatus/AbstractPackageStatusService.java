package com.sf.shiva.oms.psm.service.packagestatus;

import org.apache.commons.lang3.StringUtils;

import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.enumtype.EventEnum;

/**
 * 
 * 描述：包裹状态抽象类
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
public abstract class AbstractPackageStatusService<T> implements PackageStatusService<T> {
	
	/**
	 * 获取事件代码，若获取到事件代码不为空且非无效事件，则进行业务逻辑计算	
	 * @param obj 处理对象信息
	 * @return 包裹状态流水信息
	 * @see com.sf.shiva.oms.psm.service.packagestatus.PackageStatusService#excute(java.lang.Object)
	 * @author 01369626
	 * @date 2018年1月9日
	 */
	@Override
	public PackageStatusRecordEntity excute(T obj) {
		String eventCode = queryEventCode(obj);
		if (StringUtils.isNotEmpty(eventCode) && !EventEnum.EVENT00_0000.getEventCode().equals(eventCode)) {
			return cal(obj, eventCode);
		}
		return null;
	}
	
	/**
	 * 获取事件编码
	 * @param obj 接收的对象
	 * @return 事件编码
	 * @author 01159741
	 * @date 2017年11月28日
	 */
	protected abstract String queryEventCode(T obj);
	
	/**
	 * 业务逻辑计算
	 * @param obj 接收的对象
	 * @param eventCode 事件编码
	 * @author 01159741
	 * @date 2017年11月28日
	 */
	protected abstract PackageStatusRecordEntity cal(T obj, String eventCode);

}
