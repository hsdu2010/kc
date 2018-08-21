package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.KafkaWriteManager;

public interface KafkaWriteMgrExtMapper {
	
	public List<KafkaWriteManager> searchByName(String name);
}
