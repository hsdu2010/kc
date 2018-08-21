package com.sf.shiva.oms.ht.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.dto.CommonOrderStatusDto;
import com.sf.shiva.oms.ht.service.IOrderStateService;
import com.sf.shiva.oms.ht.service.util.CustomKafkaProducer;
import com.sf.shiva.oms.ht.service.util.JsonUtil;

/**
 * 
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID    DATE            PERSON            REASON
 *  1     2017年12月6日         835897           Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 835897
 * @version 1.0
 */
@Service
public class OrderStateService implements IOrderStateService {

    @Value("${order.state.monitor.url:http://mommon-other2.intsit.sfdc.com.cn:1080/mom-mon/monitor/requestService.pub}")
    private String monitorUrl;
    @Value("${order.state.cluster:other2}")
    private String clusterName;
    @Value("${order.state.topic:SGS_EXP_CORE_PICKUP_OMS_ORDER_STATE}")
    private String topic;
    @Value("${order.state.token:Xr7hXQl*}")
    private String token;
    
    @Override
    public void createOrderState(CommonOrderStatusDto dto) {
        String content = JsonUtil.encode2json(dto);
        CustomKafkaProducer producer = new CustomKafkaProducer();
        producer.setMonitorUrl(monitorUrl);
        producer.setClusterName(clusterName);
        producer.setTopic(topic);
        producer.setTopicTokens(topic + ":" + token);
        producer.setProducerPoolSize(4);
        try {
            producer.initProducer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        producer.sendString(content);
    }


}
