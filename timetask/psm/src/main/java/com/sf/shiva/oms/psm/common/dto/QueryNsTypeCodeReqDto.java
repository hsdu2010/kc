package com.sf.shiva.oms.psm.common.dto;

import java.util.Date;

/**
 * 
 * 描述：号段类型代码查询请求dto
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年10月15日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class QueryNsTypeCodeReqDto {
    //请求Id
    private String requestId;
    //系统编码
    private String systemCode;
    //号段代码
    private String nsCode;
    //操作时间
    private Date operateTm;
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public String getSystemCode() {
        return systemCode;
    }
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
    public String getNsCode() {
        return nsCode;
    }
    public void setNsCode(String nsCode) {
        this.nsCode = nsCode;
    }
    public Date getOperateTm() {
        return operateTm;
    }
    public void setOperateTm(Date operateTm) {
        this.operateTm = operateTm;
    }
    
    
}
