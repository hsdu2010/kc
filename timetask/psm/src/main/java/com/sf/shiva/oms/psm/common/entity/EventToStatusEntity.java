package com.sf.shiva.oms.psm.common.entity;

import java.io.Serializable;

/**
 * 
 * 描述：事件与状态对应关系配置类
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
public class EventToStatusEntity implements Serializable {
    private static final long serialVersionUID = 480714582801721746L;

    private String eventCode;// 事件代码
    private String statusCode;// 状态代码

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
