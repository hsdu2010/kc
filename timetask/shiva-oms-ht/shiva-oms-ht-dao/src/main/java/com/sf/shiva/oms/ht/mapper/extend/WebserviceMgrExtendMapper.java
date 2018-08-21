package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.WebserviceManager;

public interface WebserviceMgrExtendMapper {
	
	public List<WebserviceManager> searchByName(String name);
}
