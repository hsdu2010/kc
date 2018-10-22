package com.sf.shiva.oms.psm.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 描述：包裹状态常量类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月4日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class PackageStatusConstant {

	private PackageStatusConstant() {
	}
	
	public static final String ENCODING_UTF8 = "UTF-8";
	/** 系统运行环境，研发环境 */
	public static final String SYSTEM_ENV_DEV = "DEV";
	/** 下划线 */
	public static final String UNDER_LINE = "_";
	/** 中划线 */
	public static final String MIDCOURT_LINE = "-";
	/**系统编码*/
	public static final String SYSTEM_CODE = "SHIVA-OMS-PSM";
	/** 派件状态响应码41004(到方改派件时间) */
	public static final String RESPONSE_CODE_41004 = "41004";
	/** 派件状态响应码41005(无法联系到派件客户) */
	public static final String RESPONSE_CODE_41005 = "41005";
	/** 派件状态响应码41006(到方周末不在) */
	public static final String RESPONSE_CODE_41006 = "41006";
	/** 派件状态响应码41007(改派件员) **/
    public static final String RESPONSE_CODE_41007 = "41007";

	/** 海关通关中typeCode **/
	public static final String GWB04 = "GWB04";
	/** 31巴枪码 */
	public static final String OPCODE_31 = "31";
	/** 44巴枪码 */
	public static final String OPCODE_44 = "44";
	/** 50巴枪码 */
	public static final String OPCODE_50 = "50";
	/** 70巴枪码 */
	public static final String OPCODE_70 = "70";
	/** 80巴枪码 */
	public static final String OPCODE_80 = "80";
	/** 99巴枪码 */
	public static final String OPCODE_99 = "99";
	/** 125巴枪码 */
	public static final String OPCODE_125 = "125";
	/** 130巴枪码 */
	public static final String OPCODE_130 = "130";
	/** 664巴枪码 */
	public static final String OPCODE_664 = "664";
	/** 204巴枪码 */
	public static final String OPCODE_204 = "204";
	/**626巴枪码*/
	public static final String OPCODE_626 = "626";
	/**627巴枪码*/
	public static final String OPCODE_627 = "627";
	/**648巴枪码*/
	public static final String OPCODE_648 = "648";
	/** 异常码34 */
	public static final String STAY_WHY_CODE_34 = "34";
	/** 异常码7 */
	public static final String STAY_WHY_CODE_7 = "7";
	/** 异常码5 */
	public static final String STAY_WHY_CODE_5 = "5";
	/** 异常码65 */
	public static final String STAY_WHY_CODE_65 = "65";
	/** 异常码46 */
	public static final String STAY_WHY_CODE_46 = "46";
	/** 取消寄件扩展字段标志 */
	public static final String CANCEL_EXPRESS_FLAG = "sgs-exp-unite";
	/** 转寄扩展字段标志 */
	public static final String FORWARD_FLAG = "1";
	/** 退回扩展字段标志 */
	public static final String SENDBACK_FLAG = "2";
	/** 子运单对象类型编码 */
	public static final String SUB_WAYBILL_TYPE_CODE = "10";
	/** 运单对象类型编码 */
	public static final String WAYBILL_TYPE_CODE = "20";
	
	/** SGS派件状态响应码与巴枪的操作码_异常码对应关系Map **/
	protected static final Map<String, String> RESPONSECODE_MAPPING_OPCODE_WHYCODE = new HashMap<>();

	static {
		RESPONSECODE_MAPPING_OPCODE_WHYCODE.put(RESPONSE_CODE_41004, OPCODE_70 + UNDER_LINE + STAY_WHY_CODE_7);
		RESPONSECODE_MAPPING_OPCODE_WHYCODE.put(RESPONSE_CODE_41005, OPCODE_70 + UNDER_LINE + STAY_WHY_CODE_5);
		RESPONSECODE_MAPPING_OPCODE_WHYCODE.put(RESPONSE_CODE_41006, OPCODE_70 + UNDER_LINE + STAY_WHY_CODE_65);
	}
	
	public static Map<String, String> getRespCodeToOpWhyCodeMap(){
	    return RESPONSECODE_MAPPING_OPCODE_WHYCODE;
	}
}
