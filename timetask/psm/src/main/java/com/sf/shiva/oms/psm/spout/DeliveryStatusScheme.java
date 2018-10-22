package com.sf.shiva.oms.psm.spout;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.dto.deliverystatus.DeliveryStatusRequest;
import com.sf.shiva.oms.psm.common.enumtype.FieldKeyEnum;
import com.sf.shiva.oms.psm.common.utils.XMLUtils;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * 描述： DeliveryStatus派件状态转换类
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
public class DeliveryStatusScheme implements Scheme {
	private static final long serialVersionUID = -6958202482014562118L;
	
	private static final Logger logger = LoggerFactory.getLogger(DeliveryStatusScheme.class);

	@Override
	public List<Object> deserialize(byte[] paramArrayOfByte) {
	    DeliveryStatusRequest deliveryStatusRequest = null;
	    String msg = null;
		try {
			msg = new String(paramArrayOfByte, PackageStatusConstant.ENCODING_UTF8);
			deliveryStatusRequest = XMLUtils.toBean(msg, DeliveryStatusRequest.class);
		} catch (Exception e) {
			logger.error("DeliveryStatusScheme error. msg={}, exception={}", msg, e);
		}
		return new Values(deliveryStatusRequest);
	}

	@Override
	public Fields getOutputFields() {
		return new Fields(FieldKeyEnum.FIELD_DELIVERY_STATUS_CAL_BOLT.getKey());
	}
}
