package com.sunshanpeng.devops.common.exception;

/**
 * 执行异常，本来应该成功但是因为数据或者外部依赖出的问题
 */
public class ExecException extends RuntimeException {

    public ExecException(String message) {
        super(message);
    }
}
