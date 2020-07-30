package com.sunshanpeng.devops.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T model;
    private boolean success;
    private String errorMessage;

    public static <T> BaseResponse<T> createSuccessResult(T model) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setModel(model);
        response.setSuccess(true);
        return response;
    }

    public static <T> BaseResponse<T> createFailResult(String errorMessage) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setErrorMessage(errorMessage);
        response.setSuccess(false);
        return response;
    }
}
