package com.sf.shiva.oms.ht.service.kafka;

import com.sf.shiva.oms.ht.domain.KafkaWriteManager;

public interface KafkaWriteService {
	
	public boolean sendMsg(KafkaWriteManager kafkaWriteManager);
}
