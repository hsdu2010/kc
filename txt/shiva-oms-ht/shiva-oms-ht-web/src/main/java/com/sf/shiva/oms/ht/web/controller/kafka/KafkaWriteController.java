package com.sf.shiva.oms.ht.web.controller.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.shiva.oms.ht.domain.KafkaWriteManager;
import com.sf.shiva.oms.ht.service.kafka.KafkaWriteService;

@RequestMapping("kafka")
@Controller
public class KafkaWriteController {
	private Logger logger = LoggerFactory.getLogger(KafkaWriteController.class);

	@Autowired
	private KafkaWriteService kafkaWriteService;

	@RequestMapping("write")
	@ResponseBody
	public Boolean kafkaWrite(KafkaWriteManager kafkaWriteManager) {
		if (kafkaWriteManager == null) {
			throw new IllegalStateException("参数kafkaWriteManager不能为空");
		}
		if (kafkaWriteManager.getClusterId() == null || "".equals(kafkaWriteManager.getClusterId().trim())) {
			throw new IllegalStateException("ClusterId属性不能为空");
		}
		if (kafkaWriteManager.getTopic() == null || "".equals(kafkaWriteManager.getTopic().trim())) {
			throw new IllegalStateException("topic属性不能为空");
		}
		if (kafkaWriteManager.getToken() == null || "".equals(kafkaWriteManager.getToken().trim())) {
			throw new IllegalStateException("Token属性不能为空");
		}
		if (kafkaWriteManager.getWriteType() == null || "".equals(kafkaWriteManager.getWriteType().trim())) {
			throw new IllegalStateException("WriteType属性不能为空");
		}
		if (kafkaWriteManager.getMessage() == null || "".equals(kafkaWriteManager.getMessage().trim())) {
			throw new IllegalStateException("Message属性不能为空");
		}
		try {
			return kafkaWriteService.sendMsg(kafkaWriteManager);
		} catch (Exception e) {
			logger.error("KafkaWriteController kafkaWrite error", e);
		}
		return false;
	}

}
