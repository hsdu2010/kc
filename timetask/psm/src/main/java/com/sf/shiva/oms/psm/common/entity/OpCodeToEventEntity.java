package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;

/**
 * 
 * 描述：操作码与事件对应关系配置类
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
public class OpCodeToEventEntity implements Serializable {

    private static final long serialVersionUID = 4261118939496839686L;

    private String opCode;// 操作码
    private String eventCode;// 事件代码
    private String desc;// 描述

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
