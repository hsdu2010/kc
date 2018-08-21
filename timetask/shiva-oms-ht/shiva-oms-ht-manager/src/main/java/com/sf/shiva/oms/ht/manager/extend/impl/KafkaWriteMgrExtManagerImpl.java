package com.sf.shiva.oms.ht.manager.extend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.domain.KafkaWriteManager;
import com.sf.shiva.oms.ht.manager.extend.KafkaWriteMgrExtManager;
import com.sf.shiva.oms.ht.mapper.extend.KafkaWriteMgrExtMapper;

@Service
public class KafkaWriteMgrExtManagerImpl implements KafkaWriteMgrExtManager{
	
	@Autowired
	private KafkaWriteMgrExtMapper kafkaWriteMgrExtMapper;

	@Override
	public List<KafkaWriteManager> searchByName(String name) {	
		return kafkaWriteMgrExtMapper.searchByName(name);
	}

}
