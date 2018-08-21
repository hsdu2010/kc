package com.sf.shiva.oms.ht.service.util;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.OffsetRequest;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.TopicAndPartition;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.MessageAndOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.sf.shiva.oms.ht.service.kafka.KafkaReaderCallbackService;

public class CustomKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CustomKafkaConsumer.class);

    public static void run(long maxReads, String a_topic, int a_partition, Map<String, Integer> a_seedBrokers, KafkaReaderCallbackService reader) throws Exception {
        // 获取指定Topic partition的元数据
        PartitionMetadata metadata = findLeader(a_seedBrokers, a_topic, a_partition);
        String leadBroker = metadata.leader().host();
        int port = metadata.leader().port();
        String clientName = "Client_" + a_topic + "_" + a_partition;
        SimpleConsumer consumer = new SimpleConsumer(leadBroker, port, 100000, 64 * 1024, clientName);
        long earliest = getOffsetBy(consumer, a_topic, a_partition, OffsetRequest.EarliestTime(), clientName);
        long lastOffset = getOffsetBy(consumer, a_topic, a_partition, OffsetRequest.LatestTime(), clientName);
        long readOffset = Math.max(lastOffset - maxReads, earliest); // 从这里开始读
        long count = lastOffset - readOffset;
        stopLoop:while (count > 0) {
            FetchRequest req = new FetchRequestBuilder().clientId(clientName).addFetch(a_topic, a_partition, readOffset, 100000).build();
            FetchResponse fetchResponse = consumer.fetch(req);
            if (!fetchResponse.hasError()) {
                for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(a_topic, a_partition)) {
                    long currentOffset = messageAndOffset.offset();
                    ByteBuffer payload = messageAndOffset.message().payload();
                    byte[] bytes = new byte[payload.limit()];
                    payload.get(bytes);
                    readOffset = messageAndOffset.nextOffset();
                    reader.callback(bytes, currentOffset, a_partition);
                    count --;
                    if(count <0){
                        break stopLoop;
                    }
                }
            } else {
                break;
            }
        }
        consumer.close();

    }

    public static long getOffsetBy(SimpleConsumer consumer, String topic, int partition, long whichTime, String clientName) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
        kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(), clientName);
        OffsetResponse response = consumer.getOffsetsBefore(request);
        if (response.hasError()) {
            return 0;
        }
        long[] offsets = response.offsets(topic, partition);
        return offsets[0];
    }

    private static PartitionMetadata findLeader(Map<String, Integer> a_seedBrokers, String a_topic, int a_partition) {
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
        return returnMetaData;
    }

}
