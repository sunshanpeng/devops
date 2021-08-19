package com.sunshanpeng.devops.common.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T data;
    private boolean success;
    private Integer code;
    private String message;
    private LocalDateTime timestamp;
    private String tips;

    public static <T> BaseResponse<T> createSuccessResult(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setData(data);
        response.setSuccess(true);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    public static <T> BaseResponse<T> createFailResult(String errorMessage) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(errorMessage);
        response.setSuccess(false);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }
}
