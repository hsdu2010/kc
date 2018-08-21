package com.sf.shiva.oms.ht.service.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.kafka.api.produce.IKafkaProducer;
import com.sf.kafka.api.produce.ProduceConfig;
import com.sf.kafka.api.produce.ProducerPool;

public class CustomKafkaProducer{
	private static Logger logger = LoggerFactory.getLogger(CustomKafkaProducer.class);

	private String monitorUrl;
	private String clusterName;
	private String topic;
	private String topicTokens;
	private int producerPoolSize = 5;
	private boolean createPoolOnStart = true;
	private boolean kafkaActive = false;
	private IKafkaProducer kafkaProducer = null;
	private ProduceConfig produceConfig = null;

	private static IKafkaProducer createKafkaProducer(ProduceConfig produceConfig) {
		try {
			if (produceConfig != null) {
				final IKafkaProducer producer = new ProducerPool(produceConfig);
				Runtime.getRuntime().addShutdownHook(new Thread() {
					@Override
					public void run() {
						try {
							producer.close();
						} catch (Exception e) {
							logger.error("createKafkaProducer close error:", e);
						}
					}
				});
				return producer;
			}
		} catch (Exception e) {
			logger.error("Kafka producer init exception.", e);
		}
		return null;
	}

	protected IKafkaProducer getKafkaProducer() {
		if (this.kafkaActive && null == this.kafkaProducer) {
			synchronized (this) {
				if (this.kafkaActive && null == this.kafkaProducer) {
					this.kafkaActive = (this.kafkaProducer = createKafkaProducer(produceConfig)) != null;
				}
			}
		}
		return this.kafkaActive ? this.kafkaProducer : null;
	}

	public boolean ifKafkaActive() {
		return this.kafkaActive && getKafkaProducer() != null;
	}

	public void initProducer() throws Exception {
		if (StringUtils.isNotEmpty(monitorUrl) && StringUtils.isNotEmpty(topicTokens)
				&& StringUtils.isNotEmpty(clusterName)) {
			this.produceConfig = new ProduceConfig(producerPoolSize, monitorUrl, clusterName, topicTokens);
			this.kafkaActive = true;
			if (createPoolOnStart) {
				getKafkaProducer();
			}
			logger.info(
					"Kafka producer with clusterName \"{}\" topicTokens [{}], createPoolOnStart : {}, active status : {}.",
					clusterName, topicTokens, createPoolOnStart, kafkaActive);
		}
	}

	/**
	 * 批量发送消息
	 * 
	 * @param messages
	 *            消息内容
	 */
	public void batchSendBytes(List<byte[]> messages) {
		if (ifKafkaActive()) {
			getKafkaProducer().batchSendBytes(topic, messages);
		}
	}

	/**
	 * 批量发送消息
	 * 
	 * @param messages
	 *            消息内容
	 */
	public void batchSendString(List<String> messages) {
		if (ifKafkaActive()) {
			getKafkaProducer().batchSendString(topic, messages);
		}
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 *            消息内容
	 */
	public boolean sendBytes(byte[] message) {
		if (ifKafkaActive()) {
			getKafkaProducer().sendBytes(topic, message);
			return true;
		}
		return false;
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 *            消息内容
	 * @return boolean 发送是否成功
	 */
	public boolean sendString(String message) {
		if (ifKafkaActive()) {
			getKafkaProducer().sendString(topic, message);
			return true;
		}
		return false;
	}


	public String getMonitorUrl() {
		return monitorUrl;
	}

	public void setMonitorUrl(String monitorUrl) {
		this.monitorUrl = monitorUrl;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopicTokens() {
		return topicTokens;
	}

	public void setTopicTokens(String topicTokens) {
		this.topicTokens = topicTokens;
	}

	public int getProducerPoolSize() {
		return producerPoolSize;
	}

	public void setProducerPoolSize(int producerPoolSize) {
		this.producerPoolSize = producerPoolSize;
	}

	public boolean isCreatePoolOnStart() {
		return createPoolOnStart;
	}

	public void setCreatePoolOnStart(boolean createPoolOnStart) {
		this.createPoolOnStart = createPoolOnStart;
	}
}
