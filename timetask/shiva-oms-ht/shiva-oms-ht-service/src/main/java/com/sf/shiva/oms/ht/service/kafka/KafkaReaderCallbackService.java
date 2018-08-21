package com.sf.shiva.oms.ht.service.kafka;

public interface KafkaReaderCallbackService {
    
    public boolean callback(byte[] msg, long offset, int parition);
    
}
