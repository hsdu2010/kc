package com.sf.shiva.oms.ht.mapper.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.KafkaReadManager;

public interface KafkaReadMgrExtMapper {
	
	public List<KafkaReadManager> searchByName(String name);
	
}
