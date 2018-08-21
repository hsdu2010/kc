package com.sf.shiva.oms.ht.domain.vo;

import java.util.LinkedHashMap;

public class SideMenu {
	private String resourceName;
	
	private String resourceUrl;
	
	private LinkedHashMap<String, String> childMenu;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public LinkedHashMap<String, String> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(LinkedHashMap<String, String> childMenu) {
		this.childMenu = childMenu;
	}
}
