package com.sf.shiva.oms.ht.domain.dto;

import java.util.Date;

import com.sf.shiva.oms.ht.common.utils.UUID22;

/**
 * 
 * 描述：包裹状态流水扩展信息对象
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
public class WaybillPackageOperateProperty {
	private String id;// 主键
    private String orderNo;// 预约号
    private String packageStatusId ; //对应流水的运单ID
    private String operateId ;          //操作ID
    private String createTm;// 创建时间
    private String key;            //属性
    private String value ;          //属性值
    private String appointIntrNo;
    
    public WaybillPackageOperateProperty(){
    	this.id = UUID22.getUUID22();
    }
    
    public WaybillPackageOperateProperty(String appointIntrNo,String key,String value,String packageStatusId,String operateId){
        this.id = UUID22.getUUID22();
        this.orderNo = appointIntrNo;
        this.packageStatusId = packageStatusId;
        this.operateId = operateId;
        this.key = key;
        this.value = value;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getCreateTm() {
		return createTm;
	}

	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAppointIntrNo() {
		return appointIntrNo;
	}

	public void setAppointIntrNo(String appointIntrNo) {
		this.appointIntrNo = appointIntrNo;
	}
    
    
}
