package com.sf.shiva.oms.ht.service;

/**
 * 
 * 描述：包裹状态服务
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月29日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public interface PackageStatusService {
	
	public boolean sendPackageStatusMsg(String packageNo, String zoneCode, String packageStatus, String opCode, String orderNo, String operateEmpCode);
}
