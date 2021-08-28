package com.sunshanpeng.devops.common.exception;

/**
 * 参数异常，给的参数无法处理
 */
public class ParamException extends RuntimeException {
    public ParamException(String message) {
        super(message);
    }
}
