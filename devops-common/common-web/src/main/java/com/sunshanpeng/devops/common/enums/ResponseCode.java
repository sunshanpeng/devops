package com.sunshanpeng.devops.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(200, "success"),
    BAD_REQUEST(400, "Bad Request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    ;

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
