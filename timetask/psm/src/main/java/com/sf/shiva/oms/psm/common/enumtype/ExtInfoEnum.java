package com.sf.shiva.oms.psm.common.enumtype;

/**
 * 
 * 描述：扩展信息枚举类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月11日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public enum ExtInfoEnum {
    COURIER_CODE("courierCode", "收派员工号", "fvpBar-courierCode"),
    SIGNED_PASS_WORD("signedPassword", "口令签收密码", "204-fvpBar-extendAttach1"),
    WAYBILL_NO("waybillNo", "运单号(旧运单)", "fvpBar-waybillNo"),
    PHONE("phone", "收派员手机号码", "fvpBar-phone"),
    PHONE_ZONE("phoneZone", "代签人,签收人", "fvpBar-phoneZone"),
    ALTERD_CONSIGNEE_NAME("extendAttach10", "目标收件人（更改后的收件人）姓名", "80-fvpBar-extendAttach10"),
    ALTERD_CONSIGNEE_PHONE("extendAttach11", "联系方式", "80-fvpBar-extendAttach11"),
    ELECTRONIC_SIGN_FLAG("electronicSignFlag", "是否电子签收的单", "80-fvpBar-extendAttach8"),
    NEW_WAYBILL_NO("newWaybillNo", "存换单的新运单号", "70-fvpBar-extendAttach1"),
    CONSIGNOR_NAME("consignorName", "寄件人姓名", "70-fvpBar-extendAttach10"),
    CONSIGNOR_PHONE("consignorPhone", "寄件人电话", "70-fvpBar-extendAttach11"),
    CONSIGNEE_PHONE("consigneePhone", "收件员电话号码", "70-fvpBar-extendAttach12"),
    FC_CABINET_NO("fcCabinetNo", "柜机编码", "125-fvpBar-extendAttach5"),
    FC_CABINET_ADDR("fcCabinetAddr", "丰巢柜地址", "125-fvpBar-extendAttach6"),
    FC_CONSUMER_PICK_UP_NO("fcConsumerPickUpNo", "用户取件码", "125-fvpBar-extendAttach7"),
    FC_DELIVER_PICK_UP_NO("fcDeliverPickUpNo", "收派员取件码", "125-fvpBar-extendAttach8"),
    ALLOGRAPH_ADDRESS("extendAttach16", "代签地点" , "80-fvpBar-extendAttach16"),
    COST_TYPE("extendAttach12", "费用类型", "80-fvpBar-extendAttach12"),
    PAYMENT_MODE("extendAttach13", "付款方式", "80-fvpBar-extendAttach13"),
    MONTHLY_STATEMENT_NUMBER("extendAttach14", "月结卡号", "80-fvpBar-extendAttach14"),
    ALTER_PAYMENT_FLAG("extendAttach41", "改付标记", "50-fvpBar-extendAttach41"),
    ADMIN_CONFIRM_FLAG("extendAttach18", "管理员确认标记", "80-fvpBar-extendAttach18"),
    ALTER_TOUCHPOINT_FLAG("extendAttach3", "接触点编码", "664-fvpBar-extendAttach3"),
    ALTER_CONVENIENTSTORE_FLAG("accountantCode", "便利店编码", "130-fvpBar-accountantCode;50-fvpBar-accountantCode"),
    IDENTITY_VERIFY_FLAG("extendAttach2", "派件身份验证标识", "204-fvpBar-extendAttach2"),
    NEW_WAYBILL("extendAttach3", "新旧运单关联的新运单号", "648-fvpBar-extendAttach3"),
    FORWARD_COMPANY_NAME("extendAttach3", "转寄第三方公司名称", "626-fvpBar-extendAttach3"),
    FORWARD_WAYBILL_NO("extendAttach4", "转寄第三方单号", "626-fvpBar-extendAttach4");
    
    //扩展信息字段名
    private String name;
    //扩展信息描述
    private String desc;
    //字段来源
    private String source;
    
    private ExtInfoEnum(String name, String desc, String source) {
        this.name = name;
        this.desc = desc;
        this.source = source;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String desc() {
        return this.desc;
    }
    
    public String getSource() {
        return this.source;
    }
}
