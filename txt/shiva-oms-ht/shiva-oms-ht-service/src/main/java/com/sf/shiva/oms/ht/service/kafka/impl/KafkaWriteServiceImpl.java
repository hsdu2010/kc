package com.sf.shiva.oms.ht.service.kafka.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.fvp.ConvertUtil;
import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.ht.domain.KafkaClusterCfg;
import com.sf.shiva.oms.ht.domain.KafkaWriteManager;
import com.sf.shiva.oms.ht.service.kafka.KafkaWriteService;
import com.sf.shiva.oms.ht.service.kafkamanager.KafkaClusterCfgManagerService;
import com.sf.shiva.oms.ht.service.util.CustomKafkaProducer;
import com.sf.shiva.oms.ht.service.util.JsonUtil;

@Service
public class KafkaWriteServiceImpl implements KafkaWriteService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KafkaClusterCfgManagerService kafkaClusterCfgService;

	@Override
	public boolean sendMsg(KafkaWriteManager kafkaWriteManager) {
		boolean result = false;
		CustomKafkaProducer producer = new CustomKafkaProducer();
		KafkaClusterCfg kafkaClusterCfg = kafkaClusterCfgService.selectByPrimaryKey(kafkaWriteManager.getClusterId());
		producer.setMonitorUrl(kafkaClusterCfg.getMonitorUrl());
		producer.setClusterName(kafkaClusterCfg.getClusterName());
		producer.setTopic(kafkaWriteManager.getTopic());
		producer.setTopicTokens(kafkaWriteManager.getTopic() + ":" +kafkaWriteManager.getToken());
		producer.setProducerPoolSize(4);
		try {
			producer.initProducer();
			if ("1".equals(kafkaWriteManager.getWriteType())) {
				String msg = kafkaWriteManager.getMessage();
				result = producer.sendString(msg);
			} else if ("2".equals(kafkaWriteManager.getWriteType())) {
			    FactRouteDto dto = JsonUtil.decode(kafkaWriteManager.getMessage(), FactRouteDto.class);
				byte[] byteStr = ConvertUtil.toByte(dto);
				result = producer.sendBytes(byteStr);
			}
		} catch (Exception e) {
			logger.error("sendMsg error:{}", e);
		}
		return result;
	}

}
