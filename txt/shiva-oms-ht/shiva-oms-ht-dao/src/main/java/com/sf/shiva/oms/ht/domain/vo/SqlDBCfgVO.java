package com.sf.shiva.oms.ht.domain.vo;

import java.util.Date;

/**
 * 
 * 描述：SQL语句配置DTO
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月28日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class SqlDBCfgVO{
	private String sqlId;

    private String dbId;
    
    private String connName;

    private String sqlStatement;

    private String sqlName;

    private String description;

    private String paramDesc;

    private Date createTm;

    private String createEmp;

    private Date modifyTm;

    private String modifyEmp;

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}

	public String getConnName() {
		return connName;
	}

	public void setConnName(String connName) {
		this.connName = connName;
	}

	public String getSqlStatement() {
		return sqlStatement;
	}

	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public String getCreateEmp() {
		return createEmp;
	}

	public void setCreateEmp(String createEmp) {
		this.createEmp = createEmp;
	}

	public Date getModifyTm() {
		return modifyTm;
	}

	public void setModifyTm(Date modifyTm) {
		this.modifyTm = modifyTm;
	}

	public String getModifyEmp() {
		return modifyEmp;
	}

	public void setModifyEmp(String modifyEmp) {
		this.modifyEmp = modifyEmp;
	}

	@Override
	public String toString() {
		return "SqlDBCfgDto [sqlId=" + sqlId + ", dbId=" + dbId + ", connName=" + connName + ", sqlStatement="
				+ sqlStatement + ", sqlName=" + sqlName + ", description=" + description + ", paramDesc=" + paramDesc
				+ ", createTm=" + createTm + ", createEmp=" + createEmp + ", modifyTm=" + modifyTm + ", modifyEmp="
				+ modifyEmp + "]";
	}
	
    
}
