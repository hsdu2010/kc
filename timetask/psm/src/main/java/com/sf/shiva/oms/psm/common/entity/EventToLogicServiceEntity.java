package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;

/**
 * 
 * 描述：事件和逻辑服务的对应关系配置
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月28日      01159741         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01159741
 * @since 1.0
 */
public class EventToLogicServiceEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String eventCode;// 事件编码
    private String beanId;// 类对象名全名
    private String remark;// 备注信息

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
