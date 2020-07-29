package com.sunshanpeng.devops.gateway.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = "auth.white")
public class AuthWhiteUrlsProperties {
    private Set<String> urls = new HashSet<>();

    public boolean skip(String url) {
        return urls.contains(url);
    }
}
