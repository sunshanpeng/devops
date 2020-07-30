package com.sunshanpeng.devops.common.log;


import com.sunshanpeng.devops.common.enums.LogTypeEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MethodLogger {
    LogTypeEnum logType() default LogTypeEnum.FULL;
}