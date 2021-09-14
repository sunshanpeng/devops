package com.sunshanpeng.devops.common.exception;

/**
 * 系统异常，本来应该成功但是因为数据或者外部依赖出的问题
 */
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }
}
