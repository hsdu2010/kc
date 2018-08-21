package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.HttpManager;
import com.sf.shiva.oms.ht.manager.extend.HttpManagerExtendManager;
import com.sf.shiva.oms.ht.mapper.extend.HttpManagerExtendMapper;

@Service
public class HttpManagerExtendManagerImpl implements HttpManagerExtendManager{
	
	@Autowired
	private HttpManagerExtendMapper httpManagerExtendMapper;
	
	@Override
	public List<HttpManager> searchByRemark(String remark) {
		return httpManagerExtendMapper.searchByRemark(remark);
	}

}
