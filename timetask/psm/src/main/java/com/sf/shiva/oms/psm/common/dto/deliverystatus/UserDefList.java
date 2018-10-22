package com.sf.shiva.oms.psm.common.dto.deliverystatus;  //NOSONAR

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 派件任务状态接口dto(扩展信息对象)
 * @author 734618
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name = "userDefList")
public class UserDefList {
	
	private List<Property> property;

	public List<Property> getProperty() {
		return property;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}
	
}
