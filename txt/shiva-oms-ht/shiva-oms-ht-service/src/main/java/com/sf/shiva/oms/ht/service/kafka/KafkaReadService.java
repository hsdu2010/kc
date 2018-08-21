package com.sf.shiva.oms.ht.service.kafka;

import java.util.List;

import com.sf.shiva.oms.ht.dto.KafkaReaderDto;

public interface KafkaReadService {
    
    /** 读取kafka信息
     * @param dto
     * @return
     * @author 80002517
     * @date 2017年11月28日
     */
    public List<String> fetchData(KafkaReaderDto dto);
    
}
