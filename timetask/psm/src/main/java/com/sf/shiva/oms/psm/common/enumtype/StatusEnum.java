package com.sf.shiva.oms.psm.common.enumtype;

/**
 * 
 * 描述：状态枚举
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月29日      01159741         Create
 * ****************************************************************************
 * </pre>
 * @author 01159741
 * @since 1.0
 */
public enum StatusEnum {
	STATUS10("10", "包裹已揽收"),
	STATUS20("20", "包裹中转运输中"),
	STATUS30("30", "包裹待派送"),
	STATUS40("40", "包裹准备派送"),
	STATUS50("50", "包裹已派送成功"),
	STATUS60("60", "包裹待派送滞留"),
	STATUS70("70", "包裹中转滞留"),
	STATUS80("80", "海关通关中"),
	STATUS90("90", "包裹遗失损坏"),
	STATUS100("100", "包理赔状态"),
	STATUS110("110", "包裹作废"),
	STATUS120("120", "包裹派送滞留"),
	STATUS140("140", "转寄"),
	STATUS150("150", "退回"),
	STATUS3("3", "理赔请求状态结案");
	
	/**
	 * 状态代码
	 */
	private String statusCode;
	/**
	 * 状态描述
	 */
	private String statusDesc;
    
    StatusEnum(String statusCode,String statusDesc){
        this.statusCode=statusCode;
        this.statusDesc=statusDesc;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
    
}