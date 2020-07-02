package com.sunshanepng.devops.common.log;


import com.sunshanepng.devops.common.enums.LogTypeEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MethodLogger {
    LogTypeEnum logType() default LogTypeEnum.FULL;
}