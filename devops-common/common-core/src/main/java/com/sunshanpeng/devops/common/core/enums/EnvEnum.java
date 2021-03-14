package com.sunshanpeng.devops.common.core.enums;

import lombok.Getter;

@Getter
public enum EnvEnum {
    DEV("dev","开发环境"),
    TEST("test","测试环境"),
    PRE("pre","预发环境"),
    PROD("prod","生产环境"),
    ;
    private String value;
    private String label;

    EnvEnum(String value, String label) {
        this.value = value;
        this.label = label;

    }
}
