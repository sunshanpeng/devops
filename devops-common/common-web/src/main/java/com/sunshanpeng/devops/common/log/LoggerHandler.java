package com.sunshanpeng.devops.common.log;

import com.sunshanpeng.devops.common.enums.LogTypeEnum;
import com.sunshanpeng.devops.common.core.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Aspect
public class LoggerHandler {

    @Around("@annotation(methodLogger)")
    public Object logAround(ProceedingJoinPoint joinPoint, MethodLogger methodLogger) throws Throwable {
        long start = System.currentTimeMillis();
        Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String methodName = joinPoint.getSignature().getName();

        try {
            if (LogTypeEnum.FULL == methodLogger.logType() || LogTypeEnum.PARAM == methodLogger.logType()) {
                Optional<String> args = JsonUtil.toJSONString(joinPoint.getArgs());
                log.info("method: [{}], args: {}", methodName, args.orElse("error args"));
            }
        } catch (Exception var12) {
            log.warn("method: {}, args log error {}", methodName, var12.getLocalizedMessage());
        }


        Object result;
        result = joinPoint.proceed();

        try {
            if (LogTypeEnum.FULL == methodLogger.logType() || LogTypeEnum.RETURN == methodLogger.logType()) {
                long elapsedTime = System.currentTimeMillis() - start;
                log.info("method: [{}], result: {}, span: {} ms", methodName, JsonUtil.toJSONString(result), elapsedTime);
            }
        } catch (Exception var11) {
            log.warn("method: [{}], return log error {}", methodName, var11.getLocalizedMessage());
        }

        return result;
    }
}
