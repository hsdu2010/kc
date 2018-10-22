package com.sf.shiva.oms.psm.common.enumtype;

/**
 * 
 * 描述:系统来源枚举类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月29日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public enum SystemResourceEnum {
	FVP("FVP-CORE"),
	COS("COS"),
	SGS("SGS");
	
	private String key;

	private SystemResourceEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
