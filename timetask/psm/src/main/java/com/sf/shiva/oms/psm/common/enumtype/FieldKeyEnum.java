package com.sf.shiva.oms.psm.common.enumtype;

/**
 * 
 * 描述：Bolt中发送给下游系统时设置的KEY
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月1日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public enum FieldKeyEnum {
   
    FIELD_PACKAGE_STATUS_SEND_BOLT("FIELD_PACKAGE_STATUS_SEND_BOLT", "下发包裹状态流水信息-PackageStatusSendBolt"),
    FIELD_FVP_BAR_FILTER_BOLT("FIELD_FVP_BAR_FILTER_BOLT", "FVP巴枪路由信息-FvpBarFilterBolt"),
    FIELD_FVP_BAR_CAL_BOLT("FIELD_FVP_BAR_CAL_BOLT", "FVP过滤后巴枪路由信息-FvpBarCalBolt"),
	FIELD_CLAIM_FILTER_BOLT("FIELD_CLAIM_FILTER_BOLT","COS理赔信息-ClaimFilterBolt"),
	FIELD_CLAIM_CAL_BOLT("FIELD_CLAIM_CAL_BOLT","COS完成过滤信息-ClaimCalBolt"),
	FIELD_DELIVERY_STATUS_CAL_BOLT("FIELD_DELIVERY_STATUS_CAL_BOLT", "SGS派件状态信息-DeliveryStatusCalBolt");
	
    private String key;// 目标bolt name
    private String describe;// 描述

    private FieldKeyEnum(String key, String describe) {
        this.key = key;
        this.describe = describe;
    }

    public String getKey() {
        return key;
    }

    public String getDescribe() {
        return describe;
    }
}
