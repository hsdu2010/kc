package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.WebserviceManager;
import com.sf.shiva.oms.ht.manager.extend.WebserviceMgrExtendManager;
import com.sf.shiva.oms.ht.mapper.extend.WebserviceMgrExtendMapper;

@Service
public class WebserviceMgrExtendManagerImpl implements WebserviceMgrExtendManager{
	
	@Autowired
	private WebserviceMgrExtendMapper webserviceMgrExtMapper;

	@Override
	public List<WebserviceManager> searchByName(String name) {
		return webserviceMgrExtMapper.searchByName(name);
	}

}
