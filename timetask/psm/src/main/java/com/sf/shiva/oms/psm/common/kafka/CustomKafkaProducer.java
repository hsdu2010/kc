package com.sf.shiva.oms.psm.common.kafka;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.sf.kafka.api.produce.IKafkaProducer;
import com.sf.kafka.api.produce.ProduceConfig;
import com.sf.kafka.api.produce.ProducerPool;

/**
 * 描述：自定义Kafka生产者
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年3月7日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author zhangYao 568677
 * @since 1.0
 */
public class CustomKafkaProducer implements InitializingBean, Serializable {

    private static final long serialVersionUID = -5490579144130801967L;

    private static final Logger logger = LoggerFactory.getLogger(CustomKafkaProducer.class);

    private String monitorUrl;// 集群地址
    private String clusterName;// 集群
    private String topic;// 主题
    private String token;// 校验码
    private int producerPoolSize = 5;// 生产者线程数
    private boolean createPoolOnStart = true;
    private boolean kafkaActive = false;
    private IKafkaProducer kafkaProducer = null;// KAFKA生产者对象
    private ProduceConfig produceConfig = null;// KAFKA生产者配置

    /**
     * 初始化
     * 
     * @author 568677
     * @date 2017年12月1日
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isNotEmpty(monitorUrl) && StringUtils.isNotEmpty(topic) && StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(clusterName)) {
            this.produceConfig = new ProduceConfig(producerPoolSize, monitorUrl, clusterName, getTopicTokens());
            this.kafkaActive = true;
            if (createPoolOnStart) {
                getKafkaProducer();
            }
        }
    }

    /**
     * 组装TopicTokens，格式为：主题:校验码
     * 
     * @return topic:token
     * @author 568677
     * @date 2017年12月1日
     */
    private String getTopicTokens() {
        return new StrBuilder(topic).append(":").append(token).toString();
    }

    /**
     * 获取KAFKA生产者对象
     * 
     * @return 生产者对象
     * @author 568677
     * @date 2017年12月1日
     */
    private IKafkaProducer getKafkaProducer() {
        if (this.kafkaActive && null == this.kafkaProducer) {
            synchronized (this) {
                if (this.kafkaActive && null == this.kafkaProducer) {
                    this.kafkaActive = (this.kafkaProducer = createKafkaProducer(produceConfig)) != null;
                }
            }
        }
        return this.kafkaActive ? this.kafkaProducer : null;
    }

    /**
     * 创建KAFKA生产者
     * 
     * @param produceConfig
     *            KAFKA生产者配置
     * @return KAFKA生产者对象
     * @author 568677
     * @date 2017年12月1日
     */
    private static IKafkaProducer createKafkaProducer(ProduceConfig produceConfig) {
        try {
            if (produceConfig != null) {
                final IKafkaProducer producer = new ProducerPool(produceConfig);
                // 当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，当系统执行完这些钩子后，jvm才会关闭。所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作。
                Runtime.getRuntime().addShutdownHook(new CustomThread(producer));
                return producer;
            }
        } catch (Exception e) {
            logger.error("Kafka procuder init exception.", e);
        }
        return null;
    }

	/**
	 * 描述：自定义线程关闭producer（避免try-catch嵌套不友好）
	 *
	 * <pre>HISTORY
	 * ****************************************************************************
	 *  ID   DATE           PERSON          REASON
	 *  1    2017年12月29日      01369610         Create
	 * ****************************************************************************
	 * </pre>
	 * @author 01369610
	 * @since 1.0
	 */
	private static class CustomThread extends Thread {
		private IKafkaProducer producer;

		private CustomThread(IKafkaProducer producer) {
			this.producer = producer;
		}

		@Override
		public void run() {
			try {
				producer.close();
			} catch (Exception e) {
				logger.error("createKafkaProducer close error:", e);
			}
		}
	}
    
    /**
     * 判断KAFKA是否活着
     * 
     * @return true:存在；false:不存在
     * @author 568677
     * @date 2017年12月1日
     */
    public boolean ifKafkaActive() {
        return this.kafkaActive && getKafkaProducer() != null;
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
    public void sendBytes(byte[] message) {
        if (ifKafkaActive()) {
            getKafkaProducer().sendBytes(topic, message);
        }
    }

    /**
     * 发送消息
     * 
     * @param message
     *            消息内容
     * @return boolean 发送是否成功
     */
    public void sendString(String message) {
        if (ifKafkaActive()) {
            getKafkaProducer().sendString(topic, message);
        }
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public boolean isKafkaActive() {
        return kafkaActive;
    }

    public void setKafkaActive(boolean kafkaActive) {
        this.kafkaActive = kafkaActive;
    }

    public ProduceConfig getProduceConfig() {
        return produceConfig;
    }

    public void setProduceConfig(ProduceConfig produceConfig) {
        this.produceConfig = produceConfig;
    }

    public void setKafkaProducer(IKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    
}


