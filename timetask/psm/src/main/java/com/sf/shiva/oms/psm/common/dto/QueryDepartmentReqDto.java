package com.sf.shiva.oms.psm.common.dto;

import java.io.Serializable;

/**
 * 
 * 描述：
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月10日      01369521         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369521
 * @since 1.0
 */
public class QueryDepartmentReqDto implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;
	
	private String deptCode;

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}
