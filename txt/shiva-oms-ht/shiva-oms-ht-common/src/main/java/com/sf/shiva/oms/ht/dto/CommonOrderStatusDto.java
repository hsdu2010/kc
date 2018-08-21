package com.sf.shiva.oms.ht.dto;


import java.util.List;

/**
 * 回传oms普通订单的状态信息
 * 
 * @author 802269
 *
 */
public class CommonOrderStatusDto {

	/**
	 * <pre>
	 * 信息类型
	 * 		接收订单信息反馈	10001
	 * 			订单匹配收派员	10002
	 * 			正常收件	10003
	 * 			异常收件	10004
	 * 			订单取消	10005
	 * </pre>
	 */
	private String msgType;

	/**
	 * 订单号
	 */
	private String orderNO;

	/**
	 * 来源系统订单号
	 */
	private String originOrderNO;

	/**
	 * 运单号
	 */
	private String waybillNo;

	/**
	 * 收派员工号
	 */
	private String receiverCode;

	/**
	 * 收派员姓名
	 */
	private String receiverName;

	/**
	 * 收派员电话
	 */
	private String receiverMobile;

	/**
	 * 所属网点
	 */
	private String deptCode;

	/**
	 * 操作时间
	 */
	private Long operTime;

	/**
	 * 巴枪操作码,如：非正常收件为52
	 */
	private String barOpCode;

	/**
	 * 异常原因代码（若异常操作码为下错柯即53时，需要额外传递寄件方信息）
	 */
	private String exceptionCode;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
     * 扩展字段
     */
    private List<ExtendProperty> extendDefList;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	public String getOriginOrderNO() {
		return originOrderNO;
	}

	public void setOriginOrderNO(String originOrderNO) {
		this.originOrderNO = originOrderNO;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Long getOperTime() {
		return operTime;
	}

	public void setOperTime(Long operTime) {
		this.operTime = operTime;
	}

	public String getBarOpCode() {
		return barOpCode;
	}

	public void setBarOpCode(String barOpCode) {
		this.barOpCode = barOpCode;
	}

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ExtendProperty> getExtendDefList() {
        return extendDefList;
    }

    public void setExtendDefList(List<ExtendProperty> extendDefList) {
        this.extendDefList = extendDefList;
    }
    
}