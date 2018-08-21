package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.KafkaReadManager;

public interface KafkaReadMgrExtManager {
	
	public List<KafkaReadManager> searchByName(String name);
}
