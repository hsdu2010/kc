package com.sf.shiva.oms.psm.bolt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.enumtype.FieldKeyEnum;
import com.sf.shiva.oms.psm.common.enumtype.StreamIdEnum;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.service.datafilter.FvpBarDataFilterService;
import com.sf.shiva.oms.psm.service.packagestatus.PackageStatusService;
import com.sf.shiva.oms.psm.service.packagestatus.impl.FvpBarPackageStatusServiceImpl;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 
 * 描述：fvp巴枪业务逻辑计算bolt
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月8日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public class FvpBarCalBolt implements IRichBolt {

	private static final long serialVersionUID = -2487863969171561256L;

	private static final Logger logger = LoggerFactory.getLogger(FvpBarCalBolt.class);

	private transient OutputCollector collector;
	private transient PackageStatusService<FactRouteDto> fvpBarPackageStatusServiceImpl;
	private transient FvpBarDataFilterService fvpBarDataFilterServiceImpl;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
		this.collector = outputCollector;
		this.fvpBarPackageStatusServiceImpl = SpringContext.getInstance().getBean(FvpBarPackageStatusServiceImpl.class);
		this.fvpBarDataFilterServiceImpl = SpringContext.getInstance().getBean(FvpBarDataFilterService.class);
	}

	@Override
	public void execute(Tuple tuple) {
	    FactRouteDto factRouteDto = null;
		try {
			factRouteDto = (FactRouteDto) tuple.getValueByField(FieldKeyEnum.FIELD_FVP_BAR_CAL_BOLT.getKey());// 接收处理数据
			if(fvpBarDataFilterServiceImpl.filterBar(factRouteDto)) {
			    PackageStatusRecordEntity entity = fvpBarPackageStatusServiceImpl.excute(factRouteDto);
			    if (null != entity) {
			        collector.emit(StreamIdEnum.PACKAGE_STATUS_SEND.getStreamId(), tuple, new Values(entity));
			    }
			}
			collector.ack(tuple);// 业务处理成功，发送ACK
		} catch (Exception e) {
			logger.error("PackageStatusCalForBarBolt execute error.packageNo={}, exception={}", factRouteDto == null ? null : factRouteDto.getMainWaybillNo(), e);
			collector.fail(tuple);// 业务处理失败，数据需重新处理，发送fail给上级节点
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declareStream(StreamIdEnum.PACKAGE_STATUS_SEND.getStreamId(), new Fields(FieldKeyEnum.FIELD_PACKAGE_STATUS_SEND_BOLT.getKey()));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	@Override
	public void cleanup() {
		// 接口方法，关闭时调用，暂时无需实现逻辑
	}

}
