package com.sf.shiva.oms.psm.common.enumtype;

/**
 * 
 * 描述：topology的名称，可在jstorm命令中通过该name来管理topology
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public enum TopologyNameEnum {
    
    FVP_BAR("SHIVA_OMS_PSM_BAR_TOPOLOGY", "包裹状态计算-FVP巴枪"), 
    SGS_DELIVER("SHIVA_OMS_PSM_DELIVER_TOPOLOGY", "包裹状态计算-SGS派件状态"), 
    COS_CLAIM("SHIVA_OMS_PSM_COS_TOPOLOGY", "包裹状态计算-COS理赔"), 
    PACKAGE_STATUS_SEND_LOAD("SHIVA_OMS_PSM_SENDLOAD_TOPOLOGY", "包裹状态计算-定时发送");
    
    private String name;// topologyName
    private String describe;// topology描述

    private TopologyNameEnum(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }
}
