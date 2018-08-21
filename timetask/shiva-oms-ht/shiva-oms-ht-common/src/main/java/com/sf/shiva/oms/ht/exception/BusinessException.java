package com.sf.shiva.oms.ht.exception;

public class BusinessException extends Exception{
    /**  */
    private static final long serialVersionUID = -8332218936102989257L;
    private final String message;// 异常信息
    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    
}
