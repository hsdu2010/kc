package com.sf.shiva.oms.ht.domain.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * 描述：包裹状态实体类
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
public class PackageStatusDto {
	private String packageStatusId;
	private String orderNo;
	private String packageNo;
	private String packageStatus;
	private String createTime;
	private String createTimeSub;
	private String waybillNo;
	private List<WaybillPackageOperate> waybillPackageOperates;
	public String getPackageStatusId() {
		return packageStatusId;
	}
	public void setPackageStatusId(String packageStatusId) {
		this.packageStatusId = packageStatusId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	public String getPackageStatus() {
		return packageStatus;
	}
	public void setPackageStatus(String packageStatus) {
		this.packageStatus = packageStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimeSub() {
		return createTimeSub;
	}
	public void setCreateTimeSub(String createTimeSub) {
		this.createTimeSub = createTimeSub;
	}
	public String getWaybillNo() {
		return waybillNo;
	}
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	public List<WaybillPackageOperate> getWaybillPackageOperates() {
		return waybillPackageOperates;
	}
	public void setWaybillPackageOperates(List<WaybillPackageOperate> waybillPackageOperates) {
		this.waybillPackageOperates = waybillPackageOperates;
	}
	
	
}
