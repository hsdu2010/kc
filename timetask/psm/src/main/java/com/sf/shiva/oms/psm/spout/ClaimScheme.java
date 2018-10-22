package com.sf.shiva.oms.psm.spout;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.dto.CosClaimDto;
import com.sf.shiva.oms.psm.common.enumtype.FieldKeyEnum;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
/**
 * 描述：理赔任务转换类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月8日      01369521         Create
 * ****************************************************************************
 * </pre>
 * @author 01369521
 * @since 1.0
 */
public class ClaimScheme implements Scheme {
	private static final long serialVersionUID = -5867331235479224048L;

    private static final Logger logger = LoggerFactory.getLogger(ClaimScheme.class);

    @Override
    public List<Object> deserialize(byte[] paramArrayOfByte) {
        String msg = null;
        CosClaimDto dto = null;
        try {
			msg = new String(paramArrayOfByte, PackageStatusConstant.ENCODING_UTF8);
			dto = ObjectMapperUtil.toObject(msg, CosClaimDto.class);
        } catch (Exception e) {
            logger.error("ClaimScheme error. msg={}, exception={}", msg, e);
        }
        return new Values(dto);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields(FieldKeyEnum.FIELD_CLAIM_FILTER_BOLT.getKey());
    }
}
