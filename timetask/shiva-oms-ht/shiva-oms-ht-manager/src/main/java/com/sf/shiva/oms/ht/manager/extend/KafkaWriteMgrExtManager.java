package com.sf.shiva.oms.ht.manager.extend;

import java.util.List;

import com.sf.shiva.oms.ht.domain.KafkaWriteManager;

public interface KafkaWriteMgrExtManager {
	public List<KafkaWriteManager> searchByName(String name);
}
