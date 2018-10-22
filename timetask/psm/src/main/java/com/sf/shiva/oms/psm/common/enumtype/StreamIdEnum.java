package com.sf.shiva.oms.psm.common.enumtype;

/**
 * 描述：发送给下游时定义的streamId
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月12日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
public enum StreamIdEnum {
    
    PACKAGE_STATUS_SEND("packageStatusSend", "包裹状态发送流ID"),
    PACKAGE_STATUS_RESEND("packageStatusReSend", "包裹状态重新发送流ID");
    
    private String streamId;
    private String desc;
    
    private StreamIdEnum(String streamId, String desc) {
        this.streamId = streamId;
        this.desc = desc;
    }

    public String getStreamId() {
        return streamId;
    }

    public String getDesc() {
        return desc;
    }

}
