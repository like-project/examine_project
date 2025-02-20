package com.goodwill.examine.exception;

/**
 * 业务异常类
 */
public class BusinessException extends RuntimeException {
    
    private String message;
    private Integer code = 500;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
} 