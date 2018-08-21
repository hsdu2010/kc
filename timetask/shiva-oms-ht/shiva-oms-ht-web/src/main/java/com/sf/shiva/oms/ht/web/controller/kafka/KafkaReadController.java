package com.sf.shiva.oms.ht.web.controller.kafka;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.framework.domain.Result;
import com.sf.shiva.oms.ht.dto.KafkaReaderDto;
import com.sf.shiva.oms.ht.service.kafka.KafkaReadService;
import com.sf.shiva.oms.ht.service.kafka.impl.KafkaMommonGateWayService;

@RequestMapping("kafkaReadManager")
@Controller
public class KafkaReadController {
    @Autowired
    private KafkaReadService kafkaReadService;
    @Autowired
    private KafkaMommonGateWayService kafkaMommonGateWayService;
    
    @RequestMapping("fetchKafka")
    @ResponseBody
    public Result<List<String>> fetchKafka(KafkaReaderDto dto){
        Result<List<String>> result = new Result<>(false);
        try{
            if(dto == null){
                result.setErrorMessage("参数record不能为空");
                return result;
            }
            result.setObj(kafkaReadService.fetchData(dto));
            result.setSuccess(true);
        }catch(Exception e){
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }
    
    @RequestMapping("queryTopic")
    @ResponseBody
    public Result<String> queryTopic(String clusterName, String topic){
        Result<String> result = new Result<>(false);
        try{
            if(StringUtils.isBlank(clusterName) ||
                    StringUtils.isBlank(topic)){
                result.setErrorMessage("请输入必传参数");
            }
            result.setObj(kafkaMommonGateWayService.getTopicInfo(topic, clusterName));
            result.setSuccess(true);
        }catch(Exception e){
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }
    
}
