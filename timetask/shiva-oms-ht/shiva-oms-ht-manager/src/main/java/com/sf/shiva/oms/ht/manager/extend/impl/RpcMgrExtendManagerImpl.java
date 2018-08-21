package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.RpcManager;
import com.sf.shiva.oms.ht.manager.extend.RpcMgrExtendManger;
import com.sf.shiva.oms.ht.mapper.extend.RpcManagerExtendMapper;

@Service
public class RpcMgrExtendManagerImpl implements RpcMgrExtendManger{
	
	@Autowired
	private RpcManagerExtendMapper rpcMgrExtendMapper;

	@Override
	public List<RpcManager> searchByAppName(String appName) {
		return rpcMgrExtendMapper.searchByAppName(appName);
	}
}
