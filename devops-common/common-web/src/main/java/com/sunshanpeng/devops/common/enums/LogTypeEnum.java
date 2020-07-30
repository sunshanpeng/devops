package com.sunshanpeng.devops.common.enums;

public enum LogTypeEnum {
    PARAM("param log"),
    RETURN("return log"),
    FULL("param and return log");

    private String message;

    LogTypeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
