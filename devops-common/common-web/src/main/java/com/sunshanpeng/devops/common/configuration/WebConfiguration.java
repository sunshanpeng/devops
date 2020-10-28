package com.sunshanpeng.devops.common.configuration;

import com.sunshanpeng.devops.common.core.util.JsonUtil;
import com.sunshanpeng.devops.common.exception.GlobalExceptionHandler;
import com.sunshanpeng.devops.common.log.LoggerHandler;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@Configuration
public class WebConfiguration {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(JsonUtil.OBJECT_MAPPER);
        return jsonConverter;
    }

    @Bean
    @ConditionalOnMissingBean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public LoggerHandler getLoggerHandler() {
        return new LoggerHandler();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * 去掉RequestParam中字段的前后空格
     */
    @ControllerAdvice
    public static class ControllerStringParamTrimConfig {

        @InitBinder
        public void initBinder(WebDataBinder binder) {
            // 创建 String trim 编辑器
            // 构造方法中 boolean 参数含义为如果是空白字符串,是否转换为null
            // 即如果为true,那么 " " 会被转换为 null,否者为 ""
            StringTrimmerEditor propertyEditor = new StringTrimmerEditor(false);
            // 为 String 类对象注册编辑器
            binder.registerCustomEditor(String.class, propertyEditor);
        }
    }
}
