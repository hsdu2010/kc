package com.sf.shiva.oms.psm.common.enumtype;

/**
 * 
 * 描述：hbase表名信息
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
public enum TableNameEnum {

    SHIVA_OMS_PSM_PACKAGE_STATUS("shiva_oms_psm_package_status", "包裹状态表", 40), 
    SHIVA_OMS_PSM_PACKAGE_STATUS_EVENT("shiva_oms_psm_package_status_record", "包裹状态事件表", 235),
    SHIVA_OMS_PSM_PACKAGE_STATUS_SEND_LOAD("shiva_oms_psm_package_status_send_load", "包裹状态发送加载表（重试机制，写入KAFKA失败时才会有数据）", 1),
    SHIVA_OMS_PSM_PACKAGE_SETTLE_CLAIMS("shiva_oms_psm_package_settle_claims", "包裹理赔信息", 1);

    private String name;// 表名
    private String describe;// 描述
    private int parNum;// 分区数

    private TableNameEnum(String name, String describe, int parNum) {
        this.name = name;
        this.describe = describe;
        this.parNum = parNum;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public int getParNum() {
        return parNum;
    }

}
