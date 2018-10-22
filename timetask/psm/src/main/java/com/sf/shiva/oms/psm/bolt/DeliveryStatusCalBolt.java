package com.sf.shiva.oms.psm.bolt;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.common.dto.deliverystatus.DeliveryStatusRequest;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.enumtype.FieldKeyEnum;
import com.sf.shiva.oms.psm.common.enumtype.StreamIdEnum;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.service.datafilter.PackageNoCheckService;
import com.sf.shiva.oms.psm.service.datafilter.impl.PackageNoCheckServiceImpl;
import com.sf.shiva.oms.psm.service.packagestatus.PackageStatusService;
import com.sf.shiva.oms.psm.service.packagestatus.impl.DeliveryPackageStatusServiceImpl;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 描述：SGS派件状态计算Bolt
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月7日      01369610         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369610
 * @since 1.0
 */
public class DeliveryStatusCalBolt implements IRichBolt {

    /**  */
    private static final long serialVersionUID = 201760029481121497L;

    private static final Logger logger = LoggerFactory.getLogger(DeliveryStatusCalBolt.class);

    private transient OutputCollector collector;
    private transient PackageStatusService<DeliveryStatusRequest> deliveryPackageStatusServiceImpl;
    private transient PackageNoCheckService packageNoCheckServiceImpl;

    @Override
    public void prepare(@SuppressWarnings("rawtypes") Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        this.deliveryPackageStatusServiceImpl = SpringContext.getInstance().getBean(DeliveryPackageStatusServiceImpl.class);
        this.packageNoCheckServiceImpl = SpringContext.getInstance().getBean(PackageNoCheckServiceImpl.class);
    }

    @Override
    public void execute(Tuple tuple) {
        DeliveryStatusRequest deliveryStatus = null;
        try {
            deliveryStatus = (DeliveryStatusRequest) tuple.getValueByField(FieldKeyEnum.FIELD_DELIVERY_STATUS_CAL_BOLT.getKey());// 接收处理数据
            PackageStatusRecordEntity packageStatusRecordEntity = deliveryStatusCal(deliveryStatus);
            if (packageStatusRecordEntity != null) {
                collector.emit(StreamIdEnum.PACKAGE_STATUS_SEND.getStreamId(), tuple, new Values(packageStatusRecordEntity)); // 发送给下游的bolt消息
            }
            collector.ack(tuple);// 业务处理成功，发送ACK
        } catch (Exception e) {
            String txId = deliveryStatus == null ? null : deliveryStatus.getTxid();
            logger.error("DeliveryStatusCalBolt execute error.txId={}, exception={}", txId, e);
            collector.fail(tuple);// 业务处理失败，数据需重新处理，发送fail给上级节点
        }
    }

    @Override
    public void cleanup() {
        // 接口方法，关闭时调用，暂时无需实现逻辑
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declareStream(StreamIdEnum.PACKAGE_STATUS_SEND.getStreamId(), new Fields(FieldKeyEnum.FIELD_PACKAGE_STATUS_SEND_BOLT.getKey()));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    /**
     * 派件状态计算，首先过滤数据，在deliveryStatus不为空，且响应代码不为空 && 运单号不为空
     * &&运单号长度为12位时时进行业务逻辑处理，获取包裹状态记录流水对象
     * 
     * 2018年6月21日新增逻辑： 若子单childList不为null时，则说明时子单的异常，取子单号(childList第一个)进行处理
     * 
     * @param deliveryStatus
     *            SGS派件状态
     * @return 包裹状态事件对象
     * @author 01369610
     * @date 2018年1月8日
     */
    private PackageStatusRecordEntity deliveryStatusCal(DeliveryStatusRequest deliveryStatus) {
        if (null != deliveryStatus && StringUtils.isNotEmpty(deliveryStatus.getResponseCode()) && null != deliveryStatus.getWaybill()) {
            alterCalWaybillNo(deliveryStatus);//变更需要计算的运单号
            if(packageNoCheckServiceImpl.validPackageNo(deliveryStatus.getWaybill().getMainNo())) {//校验运单号是否合法
                return deliveryPackageStatusServiceImpl.excute(deliveryStatus);
            }
        }
        return null;
    }

    /**
     * 若子单号不为空，则设置运单号为子单号进行计算，否则不进行修改
     * 
     * @param deliveryStatus
     *            SGS派件状态
     * @author 01369626
     * @date 2018年6月21日
     */
    private static void alterCalWaybillNo(DeliveryStatusRequest deliveryStatus) {
        if(deliveryStatus.getWaybill().getChildList() != null) {
            List<String> childList = deliveryStatus.getWaybill().getChildList().getChild();
            if(CollectionUtils.isNotEmpty(childList)) {
                //后续计算会取waybill.mainNo作为包裹号进行计算以及组装包裹状态信息
                deliveryStatus.getWaybill().setMainNo(childList.get(0));
            }
        }
    }

}
