package com.sf.shiva.oms.ht.service.util;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.ErrorMapping;
import kafka.common.TopicAndPartition;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.Message;
import kafka.message.MessageAndOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sf.shiva.oms.ht.service.kafka.KafkaReaderCallbackService;
@Service
public class CustomKafkaConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomKafkaConsumer.class);
    private Map<String, Integer> m_replicaBrokers = new HashMap<>();  
    @Value("${ht.kafka.read.day:-3}")
    private int readDay; //获取天数
    
    public void run(long maxReads, String a_topic, int a_partition, Map<String, Integer> a_seedBrokers, long offset, KafkaReaderCallbackService reader) throws Exception {  
        // 获取指定Topic partition的元数据  
        PartitionMetadata metadata = findLeader(a_seedBrokers, a_topic, a_partition);  
        if (metadata == null) {  
            logger.info("Can't find metadata for Topic and Partition. Exiting");  
            return;  
        }  
        if (metadata.leader() == null) {  
            logger.info("Can't find Leader for Topic and Partition. Exiting");  
            return;  
        }  
        String leadBroker = metadata.leader().host();  
        int port = metadata.leader().port();
        String clientName = "Client_" + a_topic + "_" + a_partition;  
        SimpleConsumer consumer = new SimpleConsumer(leadBroker, port, 100000, 64 * 1024, clientName);
        long earliestTime = kafka.api.OffsetRequest.EarliestTime();
        long lastOffsetTime = kafka.api.OffsetRequest.LatestTime();
        long earliest = getOffsetBy(consumer, a_topic, a_partition, earliestTime, clientName);
        long lastOffset = getOffsetBy(consumer, a_topic, a_partition, lastOffsetTime, clientName);
        long readOffset = offset;
        if(offset == -1L){
            readOffset = earliest;
        }else if(offset == -2L){
            readOffset = lastOffset;
        }
        int numErrors = 0; 
        long a_maxReads = maxReads;
        long xx = lastOffset - earliest;
        if (a_maxReads > xx) {
          a_maxReads = xx;
        }
        Set<Long> sets = new HashSet<>();
        while (a_maxReads > 0) {
            if (consumer == null) {  
                consumer = new SimpleConsumer(leadBroker, port, 100000, 64 * 1024, clientName);  
            }  
            FetchRequest req = new FetchRequestBuilder().clientId(clientName).addFetch(a_topic, a_partition, readOffset, 100000).build();  
            FetchResponse fetchResponse = consumer.fetch(req);  
            if (fetchResponse.hasError()) {  
                numErrors++;  
                // Something went wrong!  
                short code = fetchResponse.errorCode(a_topic, a_partition);  
                logger.info("Error fetching data from the Broker:" + leadBroker + " Reason: " + code);  
                if (numErrors > 5)  
                    break;  
                if (code == ErrorMapping.OffsetOutOfRangeCode()) {  
                    readOffset = earliest;  
                    continue;  
                }  
                consumer.close();  
                consumer = null;  
                leadBroker = findNewLeader(leadBroker, a_topic, a_partition, a_seedBrokers.get(leadBroker));  
                continue;  
            }  
            numErrors = 0;  
            long numRead = 0;  
            for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(a_topic, a_partition)) {
                long currentOffset = messageAndOffset.offset();
                Message message = messageAndOffset.message();
                if ( readOffset < earliest) {
                    a_maxReads = -1; //强制退出
                    break;
                }
                if(!sets.contains(currentOffset)){
                    ByteBuffer payload = messageAndOffset.message().payload();  
                    byte[] bytes = new byte[payload.limit()];  
                    payload.get(bytes);
                    numRead++;
                    readOffset -= 1 ;
                    sets.add(currentOffset);
                    reader.callback(bytes, currentOffset, a_partition);
                    a_maxReads--;  
                }
                if(a_maxReads <= 0 ){
                    break;
                }
            }  
  
            if (numRead == 0) {  
                readOffset -= 1L;
                if (readOffset < earliest) {
                    break;
               }
            }
            
        }  
        if (consumer != null)  
            consumer.close();  
    }  
  
    public static long getOffsetBy(SimpleConsumer consumer, String topic, int partition, long whichTime, String clientName) {  
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);  
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();  
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));  
        kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(), clientName);  
        OffsetResponse response = consumer.getOffsetsBefore(request);  
        if (response.hasError()) {  
            logger.info("Error fetching data Offset Data the Broker. Reason: " + response.errorCode(topic, partition));  
            return 0;  
        }  
        long[] offsets = response.offsets(topic, partition);  
        return offsets[0];  
    }  
  
    /** 
     * @param a_oldLeader 
     * @param a_topic 
     * @param a_partition 
     * @param a_port 
     * @return String 
     * @throws Exception 
     *             找一个leader broker 
     */  
    private String findNewLeader(String a_oldLeader, String a_topic, int a_partition, int a_port) throws Exception {  
        for (int i = 0; i < 3; i++) {  
            boolean goToSleep = false;  
            PartitionMetadata metadata = findLeader(m_replicaBrokers, a_topic, a_partition);  
            if (metadata == null) {  
                goToSleep = true;  
            } else if (metadata.leader() == null) {  
                goToSleep = true;  
            } else if (a_oldLeader.equalsIgnoreCase(metadata.leader().host()) && i == 0) {  
                goToSleep = true;  
            } else {  
                return metadata.leader().host();  
            }  
            if (goToSleep) {  
                try {  
                    Thread.sleep(1000);  
                } catch (InterruptedException ie) {  
                }  
            }  
        }  
        throw new Exception("Unable to find new leader after Broker failure. Exiting");  
    }  
  
    private PartitionMetadata findLeader(Map<String, Integer> a_seedBrokers, String a_topic, int a_partition) {  
        PartitionMetadata returnMetaData = null;  
        loop: for (Entry<String, Integer> entry : a_seedBrokers.entrySet()) {  
            SimpleConsumer consumer = null;  
            try {  
                consumer = new SimpleConsumer(entry.getKey(), entry.getValue(), 100000, 64 * 1024, "leaderLookup");  
                List<String> topics = Collections.singletonList(a_topic);  
                TopicMetadataRequest req = new TopicMetadataRequest(topics);  
                kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);  
  
                List<TopicMetadata> metaData = resp.topicsMetadata();  
                for (TopicMetadata item : metaData) {  
                    for (PartitionMetadata part : item.partitionsMetadata()) {  
                        if (part.partitionId() == a_partition) {  
                            returnMetaData = part;  
                            break loop;  
                        }  
                    }  
                }  
            } catch (Exception e) {  
                logger.error("Error communicating with Broker [" + entry.getKey() + "] to find Leader for [" + a_topic + ", " + a_partition + "] Reason: " + e);  
            } finally {  
                if (consumer != null)  
                    consumer.close();  
            }  
        }  
        if (returnMetaData != null) {  
            m_replicaBrokers.clear();  
            for (kafka.cluster.Broker replica : returnMetaData.replicas()) {  
                String host = replica.host();
                m_replicaBrokers.put(host, a_seedBrokers.get(host));
            }  
        }  
        return returnMetaData;  
    }  
}
