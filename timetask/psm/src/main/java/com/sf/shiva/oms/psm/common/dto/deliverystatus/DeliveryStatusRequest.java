package com.sf.shiva.oms.psm.common.dto.deliverystatus; //NOSONAR

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 派件任务状态接口dto
 * @author 734618
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name = "deliveryStatusRequest")
public class DeliveryStatusRequest {

	private String txid;

	private long timestamp;
	
	private String responseType;
	
	private String responseCode;
	
	private String description;
	
	private String crouier;
	
	private String deptCode;
	
	private Long operationTime;
	
	private Waybill waybill;

	private UserDefList userDefList;

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCrouier() {
		return crouier;
	}

	public void setCrouier(String crouier) {
		this.crouier = crouier;
	}
	
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Long getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Long operationTime) {
		this.operationTime = operationTime;
	}

	public Waybill getWaybill() {
		return waybill;
	}

	public void setWaybill(Waybill waybill) {
		this.waybill = waybill;
	}

	public UserDefList getUserDefList() {
		return userDefList;
	}

	public void setUserDefList(UserDefList userDefList) {
		this.userDefList = userDefList;
	}

}
