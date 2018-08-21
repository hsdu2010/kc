package com.sf.shiva.oms.ht.dto;
/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2017年12月6日         835897           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 835897
 * @version 1.0
 */
public class OrderStateDto {

    /** 状态码 */
    private String stateCode;
    /** 来源系统订单号 */
    private String sysOrderNo;
    /** OMS订单号 */
    private String omsOrderNo;
    /** 运单号 */
    private String waybillNo;
    
    public String getStateCode() {
        return stateCode;
    }
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
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
    public String getOmsOrderNo() {
        return omsOrderNo;
    }
    public void setOmsOrderNo(String omsOrderNo) {
        this.omsOrderNo = omsOrderNo;
    }
    
}
