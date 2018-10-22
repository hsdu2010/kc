package com.sf.shiva.oms.psm.datacache.impl.provider;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.dto.QueryNsTypeCodeReqDto;
import com.sf.shiva.oms.psm.common.dto.Result;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.common.utils.PooledHttpClient;
import com.sf.shiva.oms.psm.common.utils.UUID22;
import com.sf.shiva.oms.psm.common.utils.cache.impl.ICacheProvider;

/**
 * 
 * 描述：号段类型代码缓存加载类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年10月15日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public class NsTypeCodeCacheProvider implements ICacheProvider<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(NsTypeCodeCacheProvider.class);

    @Value("${nsTypeCode_query_post_url}")
    private String postUrl;// http请求地址
    @Autowired
    private PooledHttpClient nsCfgHttpClient;

    @Override
    public String getData(String nsCode) {
        String response = nsCfgHttpClient.postRequest(createRequestMsg(nsCode), postUrl);
        String nsTypeCode = parseResponse(response);
        if(StringUtils.isEmpty(nsTypeCode)) {
            logger.error("NsTypeCodeCacheProvider query nsTypeCode error. nsCode={}, response={}", nsCode, response);
        }
        return nsTypeCode;
    }

    /**
     * 组装请求报文
     * 
     * @param nsCode
     *            号段代码
     * @return 请求报文
     * @author 01369626
     * @date 2018年10月15日
     */
    private static String createRequestMsg(String nsCode) {
        QueryNsTypeCodeReqDto reqDto = new QueryNsTypeCodeReqDto();
        reqDto.setRequestId(UUID22.getUUID22());
        reqDto.setSystemCode(PackageStatusConstant.SYSTEM_CODE);
        reqDto.setNsCode(nsCode);
        reqDto.setOperateTm(new Date());
        return ObjectMapperUtil.toJson(reqDto);
    }

    /**
     * 转换解析响应信息
     * 
     * @param response
     *            响应信息
     * @return 查询结果(号段类型代码)
     * @author 01369626
     * @date 2018年10月15日
     */
    private static String parseResponse(String response) {
        if(StringUtils.isNotEmpty(response)) {
            Result<String> result = ObjectMapperUtil.toObject(response, Result.class, String.class);
            if(null != result && result.isSuccess()) {
                return result.getObj();
            }
        }
        return null;
    }

}
