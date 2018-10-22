package com.sf.shiva.oms.psm.common.dto.deliverystatus;  //NOSONAR

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 派件任务状态接口dto
 * @author 734618
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name = "waybill")
public class Waybill {
	
	@XmlAttribute
	private String mainNo;

	private ChildList childList;
	
	public Waybill() {
		// NOSONAR
	}
	
	public Waybill(String mainNo) {
		this.mainNo = mainNo;
	}

	public Waybill(String mainNo, String... nos) {
		this.mainNo = mainNo;
		childList = new ChildList(nos);
	}

	public String getMainNo() {
		return mainNo;
	}

	public void setMainNo(String mainNo) {
		this.mainNo = mainNo;
	}

	public ChildList getChildList() {
		return childList;
	}

	public void setChildList(ChildList childList) {
		this.childList = childList;
	}

}
