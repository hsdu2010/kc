package com.sf.shiva.oms.psm.common.exception;

/**
 * 
 * 描述：自定义异常
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年9月18日      80002137         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 80002137
 * @since 1.0
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -5721193239716176585L;

    public BusinessException() {
        super();
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable t) {
        super(msg, t);
    }

    public BusinessException(Throwable t) {
        super(t);
    }

}
