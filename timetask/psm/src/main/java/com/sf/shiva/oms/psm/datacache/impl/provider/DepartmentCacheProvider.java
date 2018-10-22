package com.sf.shiva.oms.psm.datacache.impl.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.sf.shiva.oms.psm.common.dto.DepartmentDto;
import com.sf.shiva.oms.psm.common.dto.QueryDepartmentReqDto;
import com.sf.shiva.oms.psm.common.dto.Result;
import com.sf.shiva.oms.psm.common.utils.ObjectMapperUtil;
import com.sf.shiva.oms.psm.common.utils.PooledHttpClient;
import com.sf.shiva.oms.psm.common.utils.cache.impl.ICacheProvider;

/**
 * 
 * 描述：机构信息加载类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月4日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class DepartmentCacheProvider implements ICacheProvider<String, DepartmentDto> {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentCacheProvider.class);
	
    @Value("${department_ws_post_url}")
    private String postUrl;// http请求地址

    @Autowired
    private PooledHttpClient departmentCfgHttpClient;

    @Override
    public DepartmentDto getData(String deptCode) { 
        String resultStr = departmentCfgHttpClient.postRequest(createRequestJson(deptCode), postUrl);
        return parseResponse(resultStr,deptCode);
    }

    /**
     * 组装请求Json报文
     * 
     * @param deptCode
     *            机构代码
     * @return 请求Json报文
     * @author 568677
     * @date 2017年12月4日
     */
    private static String createRequestJson(String deptCode) {
    	QueryDepartmentReqDto requst = new QueryDepartmentReqDto();
    	requst.setDeptCode(deptCode);
    	return ObjectMapperUtil.toJson(requst);
    }

    /**
     * 将结果json转换为结果对象返回
     * 
     * @param resultJson
     *            结果json
     * @return 结果对象
     * @author 568677
     * @date 2017年12月4日
     */
    private static DepartmentDto parseResponse(String resultJson,String deptCode) {
		Result<DepartmentDto> result = ObjectMapperUtil.toObjectForMap(resultJson, Result.class,DepartmentDto.class);
    	if( null != result && result.isSuccess()) {
            return result.getObj();
    	}
    	logger.error("DepartmentCacheProvider get departmentDto failed msg:{} ",deptCode);
    	return null;
    }

}
