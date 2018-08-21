package com.sf.shiva.oms.ht.service.kafka.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.fvp.ConvertUtil;
import com.sf.fvp.dto.FactRouteDto;
import com.sf.kafka.check.util.AuthUtil;
import com.sf.shiva.oms.ht.dto.KafkaReaderDto;
import com.sf.shiva.oms.ht.service.kafka.KafkaReadService;
import com.sf.shiva.oms.ht.service.kafka.KafkaReaderCallbackService;
import com.sf.shiva.oms.ht.service.util.CustomKafkaConsumer;
import com.sf.shiva.oms.ht.service.util.JsonUtil;
@Component
public class KafkaReadServiceImpl implements KafkaReadService{
    
    @Autowired
    private KafkaMommonGateWayService kafkaMommonServiceGateWay;
    private static ExecutorService executor = Executors.newCachedThreadPool();
    
    @Override
    public List<String> fetchData(KafkaReaderDto dto) {
        List<String> messageList = Collections.synchronizedList(new ArrayList<>());
        try {
            int totalParition = kafkaMommonServiceGateWay.getTotalParitionNum(dto.getTopicName(), dto.getClusterName());
            List<Future<?>> futures = new ArrayList<>(totalParition);
            for(int i =0; i<totalParition; i++){
                final int partition =  i;
                futures.add(executor.submit(()->fetchDataByParition(dto, 
                            AuthUtil.getBrokers(dto.getClusterName(), dto.getToken(), dto.getMonitorUrl()), messageList, partition)));
            }
            await(futures);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageList;
    }
    
    private void await(List<Future<?>> futures) {
        futures.parallelStream().forEach(future->{
            try {
                future.get(1, TimeUnit.MINUTES);// 设置超时时间为1分钟
            } catch (InterruptedException e) {// 如果当前的线程在等待时被中断
                future.cancel(true);// 取消任务
            } catch (ExecutionException e) {// 如果计算抛出异常
                future.cancel(true);// 取消任务
            } catch (TimeoutException e) {// 线程超时
                future.cancel(true);// 取消任务
            }
        });
        futures.clear();
    }

    private void fetchDataByParition(KafkaReaderDto dto, String brokers, List<String> messageList, final int partition) {
        try {
            CustomKafkaConsumer.run(dto.getMaxSize(), dto.getTopicName(), partition, getBrokerMap(brokers), new KafkaReaderCallbackService() {
                @Override
                public boolean callback(byte[] msg, long offset, int parition) {
                    String dealMsg = filter(dto.isDeserialize(), msg, offset, parition, dto.getFilterStr());
                    if(dealMsg != null){
                        messageList.add(dealMsg);
                        return true;
                    }
                    return false;
                }
                
                private String filter(boolean deSerialize, byte[] data, long offset, int parition, String filterStr){
                    try {
                        String message;
                        if(deSerialize){
                            message = JsonUtil.encode2json(ConvertUtil.fromByte(FactRouteDto.class, data));
                        }else{
                            message = new String(data, "UTF-8");
                        }
                        if(StringUtils.isBlank(filterStr) || message.contains(filterStr)){
                          return String.format("{\"offset\":%d, \"parition\":%d, \"message\":%s}", offset, parition, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
            } catch (Exception e) {
            }
    }
    

    private static Map<String, Integer> getBrokerMap(String brokers){
        Map<String, Integer> brokerMap = new HashMap<>();
        for(String broker : brokers.split(",")){
            String[] brokerArr = broker.split(":");
            brokerMap.put(brokerArr[0], Integer.parseInt(brokerArr[1]));
        }
        return brokerMap;
    }

}
