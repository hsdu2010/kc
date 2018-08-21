package com.sf.shiva.oms.ht.dto;

public class OrderCreateDto {
	
	 /** 来源系统订单号 */
    private String sysOrderNo;
    /** 运单号 */
    private String waybillNo;
    /** 寄方城市代码 */
    private String senderCityCode;
    /** 寄方地址 */
    private String senderAddr;
    /** 到方地址 */
    private String receiverAddr;
    /** 支付方式：1-寄付 2-到付 3-第三方付 */
    private String paymentTypeCode;
    /** 预约时间 */
    private String appointTime;
	
	private String allJson;

	public String getSysOrderNo() {
		return sysOrderNo;
	}

	public void setSysOrderNo(String sysOrderNo) {
		this.sysOrderNo = sysOrderNo;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getSenderCityCode() {
		return senderCityCode;
	}

	public void setSenderCityCode(String senderCityCode) {
		this.senderCityCode = senderCityCode;
	}

	public String getSenderAddr() {
		return senderAddr;
	}

	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}

	public String getReceiverAddr() {
		return receiverAddr;
	}

	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public String getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(String appointTime) {
		this.appointTime = appointTime;
	}

	public String getAllJson() {
		return allJson;
	}

	public void setAllJson(String allJson) {
		this.allJson = allJson;
	}
}
