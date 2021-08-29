package com.sunshanpeng.devops.common.base;

import com.sunshanpeng.devops.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T data;
    private boolean success;//要不要success是个问题，因为状态码可以表示是否成功
    private Integer code;
    private String message;
    private LocalDateTime timestamp;
    private String tips;

    public static <T> BaseResponse<T> createSuccessResult(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setData(data);
        response.setCode(ResultCode.SUCCESS.getCode());
        response.setMessage(ResultCode.SUCCESS.getMessage());
        response.setSuccess(true);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    public static <T> BaseResponse<T> createFailResult(Integer code, String errorMessage) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setData(null);
        response.setCode(code);
        response.setMessage(errorMessage);
        response.setSuccess(false);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }
}
