package com.sf.shiva.oms.psm.bolt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
import com.sf.shiva.oms.psm.common.enumtype.FieldKeyEnum;
import com.sf.shiva.oms.psm.common.utils.SpringContext;
import com.sf.shiva.oms.psm.service.datafilter.ClaimDataFilterService;
import com.sf.shiva.oms.psm.service.datafilter.impl.ClaimDataFilterServiceImpl;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 
 * 描述：理赔数据过滤节点，不满足条件过滤掉
 * 满足条件不被过滤：1.package_settle_claims表中不存在当前包裹。2.当前状态为结案状态（state=3）
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月8日      01369521         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369521
 * @since 1.0
 */
public class ClaimFilterBolt implements IRichBolt {
	private static final long serialVersionUID = -2487863969171561256L;

	private static final Logger logger = LoggerFactory.getLogger(ClaimFilterBolt.class);

	private transient OutputCollector collector;
	private transient ClaimDataFilterService claimDataFilterServiceImpl;

	/**
	 * 初始化
	 * 
	 * @param map
	 * @param topologyContext
	 * @param outputCollector
	 * @see backtype.storm.task.IBolt#prepare(java.util.Map,
	 *      backtype.storm.task.TopologyContext,
	 *      backtype.storm.task.OutputCollector)
	 * @author 01369521
	 * @date 2017年12月13日
	 */
	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
		this.collector = outputCollector;
		this.claimDataFilterServiceImpl = SpringContext.getInstance().getBean(ClaimDataFilterServiceImpl.class);
	}

	/**
	 * 接收一个tuple进行处理，并用prepare方法传入的OutputCollector的ack方法（成功）或fail（失败）来反馈处理结果
	 * 
	 * @param tuple
	 * @see backtype.storm.task.IBolt#execute(backtype.storm.tuple.Tuple)
	 * @author 01369521
	 * @date 2017年12月13日
	 */
	@Override
	public void execute(Tuple tuple) {
	    CosClaimDto cosClaimDto = null;
		try {
			cosClaimDto = (CosClaimDto) tuple.getValueByField(FieldKeyEnum.FIELD_CLAIM_FILTER_BOLT.getKey());// 接收处理数据
			if (!claimDataFilterServiceImpl.filterClaims(cosClaimDto)) {// 不过滤就下发bolt
				collector.emit(tuple, new Values(cosClaimDto)); // 发送给下游的bolt消息
			}
			collector.ack(tuple);// 业务处理成功，发送ACK
		} catch (Exception e) {
		    String packageNo = cosClaimDto == null ? null : cosClaimDto.getCarryId();
			logger.error("ClaimFilterBolt execute error. packageNo={}, exception={}", packageNo, e);
			collector.fail(tuple);// 业务处理失败，数据需重新处理，发送fail给上级节点
		}
	}

	/**
	 * 为bolt声明输出模式，数据需要发送至下个组件时设置
	 * 
	 * @param outputFieldsDeclarer
	 * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
	 * @author 01369521
	 * @date 2017年12月13日
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields(FieldKeyEnum.FIELD_CLAIM_CAL_BOLT.getKey()));
	}

	/**
	 * 获取本bolt的component 配置,
	 * configMap.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS,30);可设置定时执行
	 * 
	 * @return
	 * @see backtype.storm.topology.IComponent#getComponentConfiguration()
	 * @author 01369521
	 * @date 2017年12月13日
	 */
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	/**
	 * 在关闭前调用，不保证其一定执行
	 * 
	 * @see backtype.storm.task.IBolt#cleanup()
	 * @author 01369521
	 * @date 2017年12月13日
	 */
	@Override
	public void cleanup() {
		// 接口方法，关闭时调用，暂时无需实现逻辑
	}

}
