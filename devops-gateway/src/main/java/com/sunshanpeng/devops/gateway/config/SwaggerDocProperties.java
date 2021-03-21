package com.sunshanpeng.devops.gateway.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "swagger.doc")
public class SwaggerDocProperties {
    private Boolean enabled = false;
}
