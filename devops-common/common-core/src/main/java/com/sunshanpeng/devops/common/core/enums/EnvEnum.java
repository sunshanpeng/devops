package com.sunshanpeng.devops.common.core.enums;

import lombok.Getter;

@Getter
public enum EnvEnum {
    DEV("dev","开发环境"),//也有公司叫SIT集成环境、联调环境
    TEST("test","测试环境"),//阿里系公司很多叫daily日常环境，也有公司叫UAT环境
    PRE("pre","预发环境"),//也有公司叫GA灰度环境
    PROD("prod","生产环境"),//也叫prd/pro线上环境、现网环境
    ;
    private String value;
    private String label;

    EnvEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

}
