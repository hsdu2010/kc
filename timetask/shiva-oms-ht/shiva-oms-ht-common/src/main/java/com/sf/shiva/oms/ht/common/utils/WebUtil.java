package com.sf.shiva.oms.ht.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * 描述：web工具类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月5日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public class WebUtil {

    /**
     * 获取用户ip
     * 
     * @return
     * @author 01369626
     * @date 2018年6月5日
     */
    public static String getRequestIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null) {
            return request.getRemoteAddr();
        }
        return StringUtils.EMPTY;
    }

}
