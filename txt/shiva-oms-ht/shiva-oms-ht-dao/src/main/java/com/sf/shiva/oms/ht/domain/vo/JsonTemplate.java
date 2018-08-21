package com.sf.shiva.oms.ht.domain.vo;

import java.util.Date;

public class JsonTemplate {
	
	private String id;					//主键
	private String templateType;        //模板类型   
	private String templateName;		//模板名称
	private String templateContent;		//模板内容
	private String isDel;				//是否删除（0：不删除，1：已删除）
	private Date createTm;				//创建时间
    private String createEmp;			//创建人
    private Date modifyTm;				//修改时间
    private String modifyEmp;			//修改人
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
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
	
}
