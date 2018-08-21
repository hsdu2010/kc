package com.sf.shiva.oms.ht.domain.dto;

import java.util.Date;
import java.util.List;

import com.sf.shiva.oms.ht.common.utils.UUID22;

/**
 * 
 * 描述：包裹操作
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
public class WaybillPackageOperate {
	/** 主键ID */
    protected String operateId;
    /** 操作码 */
    protected String operateCode;
    /** 操作时间 */
    protected String operateTime;    
    /** 异常原因代码 */
    protected String reasonCode;
    /** 操作员工号 */
    protected String operateEmpCode;
    /** 创建时间 */
    protected String createTime;    
    /** 顺丰订单号 */
    protected String orderNo;
    /** 包裹状态流水ID*/
    protected String packageStatusId;
    /** 操作网点*/
    protected String zoneCode; 
    private String responseType;            //(定义类型) 2-任务状态
    private String responseCode;            //定义代码
    private String description;                //描述
    private List<WaybillPackageOperateProperty> userDefList;//扩展附加属性
    
    public WaybillPackageOperate() {
		this.operateId = UUID22.getUUID22();
	}
    
    public WaybillPackageOperate(String operateCode,String reasonCode,String operateEmpCode ,String orderNo,String packageStatusId, String zoneCode ){
        this.operateId = UUID22.getUUID22();
        this.operateCode = operateCode ;
        this.reasonCode = reasonCode ;
        this.operateEmpCode = operateEmpCode ;
        this.orderNo = orderNo ;
        this.packageStatusId = packageStatusId ;
        this.zoneCode = zoneCode ;
    }

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getOperateEmpCode() {
		return operateEmpCode;
	}

	public void setOperateEmpCode(String operateEmpCode) {
		this.operateEmpCode = operateEmpCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPackageStatusId() {
		return packageStatusId;
	}

	public void setPackageStatusId(String packageStatusId) {
		this.packageStatusId = packageStatusId;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
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

	public List<WaybillPackageOperateProperty> getUserDefList() {
		return userDefList;
	}

	public void setUserDefList(List<WaybillPackageOperateProperty> userDefList) {
		this.userDefList = userDefList;
	}
    
}
