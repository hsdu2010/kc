package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sf.shiva.oms.psm.common.annotation.ColumnFamily;
import com.sf.shiva.oms.psm.common.annotation.NameSpace;
import com.sf.shiva.oms.psm.common.annotation.TableName;

/**
 * 
 * 描述：包裹状态事件对象；RowKey=分区号_包裹号_包裹状态_操作时间戳；分区号=hash(包裹号)%234
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月6日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
@NameSpace("shiva_oms_psm")
@TableName("package_status_record")
@ColumnFamily("cf1")
public class PackageStatusRecordEntity implements Serializable {

    private static final long serialVersionUID = -4078136495691649126L;

    private String id;// 包裹状态事件ID
    private String packageNo;// 包裹号
    private String packageStatus;// 包裹状态
    private String eventCode;// 事件代码
    private String operateCode;// 操作码
    private String operateReasonCode;// 操作原因代码

    private Date operateTm;// 操作时间
    private String operateEmpCode;// 操作员工号
    private String operateZoneCode;// 操作网点代码
    private String responseType;// SGS上报类型
    private String responseCode;// SGS上报代码
    private String responseDescription;// SGS上报描述
    private List<PackageStatusRecordExtendEntity> extendInfoList;// 扩展属性Map<K,V>
    private String sysSource;// 系统来源
    private Date createTm;// 创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    public String getOperateReasonCode() {
        return operateReasonCode;
    }

    public void setOperateReasonCode(String operateReasonCode) {
        this.operateReasonCode = operateReasonCode;
    }

    public Date getOperateTm() {
        return operateTm;
    }

    public void setOperateTm(Date operateTm) {
        this.operateTm = operateTm;
    }

    public String getOperateEmpCode() {
        return operateEmpCode;
    }

    public void setOperateEmpCode(String operateEmpCode) {
        this.operateEmpCode = operateEmpCode;
    }

    public String getOperateZoneCode() {
        return operateZoneCode;
    }

    public void setOperateZoneCode(String operateZoneCode) {
        this.operateZoneCode = operateZoneCode;
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

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public List<PackageStatusRecordExtendEntity> getExtendInfoList() {
        return extendInfoList;
    }

    public void setExtendInfoList(List<PackageStatusRecordExtendEntity> extendInfoList) {
        this.extendInfoList = extendInfoList;
    }

    public String getSysSource() {
        return sysSource;
    }

    public void setSysSource(String sysSource) {
        this.sysSource = sysSource;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

}
