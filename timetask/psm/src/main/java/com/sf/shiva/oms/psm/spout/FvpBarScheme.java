package com.sf.shiva.oms.psm.spout;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.fvp.ConvertUtil;
import com.sf.fvp.dto.FactRouteDto;
import com.sf.shiva.oms.psm.common.enumtype.FieldKeyEnum;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

/**
 * 
 * 描述：FVP巴枪转换类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class FvpBarScheme implements Scheme {

    private static final long serialVersionUID = -5867331235479224048L;

    private static final Logger logger = LoggerFactory.getLogger(FvpBarScheme.class);

    @Override
    public List<Object> deserialize(byte[] paramArrayOfByte) {
        FactRouteDto factRouteDto = null;
        try {
            factRouteDto = ConvertUtil.fromByte(FactRouteDto.class, paramArrayOfByte);
        } catch (Exception e) {
            logger.error("FvpBarScheme error. paramArrayOfByte={}, exception={}", paramArrayOfByte, e);
        }
        return new Values(factRouteDto);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields(FieldKeyEnum.FIELD_FVP_BAR_CAL_BOLT.getKey());
    }
}
