package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.KafkaReadManager;
import com.sf.shiva.oms.ht.manager.extend.KafkaReadMgrExtManager;
import com.sf.shiva.oms.ht.mapper.extend.KafkaReadMgrExtMapper;

@Service
public class KafkaReadMgrExtManagerImpl implements KafkaReadMgrExtManager{
	
	@Autowired
	private KafkaReadMgrExtMapper kafkaReadMgrExtMapper;

	@Override
	public List<KafkaReadManager> searchByName(String name) {
		return kafkaReadMgrExtMapper.searchByName(name);
	}

}
