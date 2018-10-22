package com.sf.shiva.oms.psm.common.dto.deliverystatus;  //NOSONAR

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 派件任务状态接口dto(扩展信息Property键值对)
 * @author 734618
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name = "property")
public class Property {
	
	@XmlAttribute
	private String key;
	
	@XmlAttribute
	private String value;
	
	public Property() {
		// Do nothing
	}
	
	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
