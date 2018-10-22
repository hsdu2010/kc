package com.sf.shiva.oms.psm.common.dto.deliverystatus;  //NOSONAR

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 派件任务状态接口dto
 * 
 * @author 734618
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "childList")
public class ChildList {

	private List<String> child;

	public ChildList() {
		// NOSONAR
	}

	public ChildList(String... nos) {
		child = new ArrayList<>();
		child.addAll(Arrays.asList(nos));
	}

	public ChildList(List<String> list) {
		this.child = list;
	}

	public List<String> getChild() {
		return child;
	}

	public void setChild(List<String> child) {
		this.child = child;
	}

}
