package com.sunshanpeng.devops.gitlab.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {
    OWNER("Owner", "可以设置项目访问权限 - Visibility Level、删除项目、迁移项目、管理组成员"),
    MASTER("Master", "可以创建项目、添加tag、保护分支、添加项目成员、编辑项目"),
    DEVELOPER("Developer", "可以克隆代码、开发、提交、push"),
    REPORTER("Reporter", "可以克隆代码，不能提交"),
    GUEST("Guest", "可以创建issue、发表评论，不能读写版本库"),
    ;
    UserTypeEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }
    private String value;
    private String description;
}
