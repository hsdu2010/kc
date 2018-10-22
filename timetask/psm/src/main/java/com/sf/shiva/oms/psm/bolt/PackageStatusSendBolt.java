package com.sf.shiva.oms.psm.bolt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.common.enumtype.FieldKeyEnum;
import com.sf.shiva.oms.psm.common.enumtype.StreamIdEnum;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.service.datasend.PackageStatusSendService;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

/**
 * 
 * 描述：包裹状态发送KAFKA Bolt
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      80002517         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002517
 * @since 1.0
 */
public class PackageStatusSendBolt implements IRichBolt {

    private static final long serialVersionUID = 2394225854219603314L;

    private static final Logger logger = LoggerFactory.getLogger(PackageStatusSendBolt.class);
    
    private transient PackageStatusSendService packageStatusSendService;
    
    private transient OutputCollector collector;
    
    
    @Override
    public void prepare(@SuppressWarnings("rawtypes") Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        this.packageStatusSendService = SpringContext.getInstance().getBean(PackageStatusSendService.class);
    }

    /** 
     * 下发逻辑：<br/>
     * 1、获取传递过来的streamId
     * 2、如果streamId为下发,则执行下发逻辑
     * 3、如果streamId为重发，则执行重发逻辑
     * @param tuple 数据对象
     * @see backtype.storm.task.IBolt#execute(backtype.storm.tuple.Tuple)
     * @author 80002517
     * @date 2018年1月11日
     */
    @Override
    public void execute(Tuple tuple) {
        try{
            String streamId = tuple.getSourceStreamId();
            Object object = tuple.getValueByField(FieldKeyEnum.FIELD_PACKAGE_STATUS_SEND_BOLT.getKey());
            if (null != object) {
            	if(StreamIdEnum.PACKAGE_STATUS_SEND.getStreamId().equals(streamId)){
                    packageStatusSendService.sendPackageStatus(object);
                }else if(StreamIdEnum.PACKAGE_STATUS_RESEND.getStreamId().equals(streamId)){
                    packageStatusSendService.reSendPackageStatus(object);
                }
			}
        }catch(Exception e){
            logger.error("PackageStatusSendKafaBolt execute error.{}", e);
        }
        collector.ack(tuple);// 业务处理成功，发送ACK
    }

    @Override
    public void cleanup() {
    	//接口方法，关闭时调用，暂时无需实现逻辑
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    	//
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
    
}
