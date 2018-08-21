package com.sf.shiva.oms.ht.common.utils;

import com.sf.framework.domain.Result;

/**
 * 
 * 描述：组装请求结果工具类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月15日      01369626         Create
 * ****************************************************************************
 * </pre>
 * @author 01369626
 * @since 1.0
 */
public class ResultUtil {
    
    private ResultUtil() {
        //隐藏构造方法
    }
    
    /**
     * 构造成功返回result
     * @param obj
     * @return
     * @author 01369626
     * @date 2018年6月15日
     */
    public static <T> Result<T> createSuccessResult(T obj){
        return createResult(true, null, null, obj);
    }
    
    public static <T> Result<T> createErrorResult(String errorCode, String errorMsg, T obj){
        return createResult(false, errorCode, errorMsg, obj);
    }
    
    public static <T> Result<T> createResult(boolean isSuccess, String errorCode, String errorMsg, T obj){
        Result<T> result = new Result<>();
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMsg);
        result.setObj(obj);
        result.setSuccess(isSuccess);
        return result;
    }
    

}
