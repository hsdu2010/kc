package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;

/**
 * 
 * 描述：操作码、原因代码与事件关系配置类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月29日      01159741         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01159741
 * @since 1.0
 */
public class OpCodeWhyCodeToEventEntity implements Serializable {

    private static final long serialVersionUID = -3770871033136320422L;

    private String opCode;// 操作码
    private String reasonCode;// 原因代码
    private String eventCode;// 事件代码

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

}
