package com.sunshanpeng.devops.member.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
@ConfigurationProperties("devops")
public class DevopsConfig {
    private List<String> admin = new LinkedList<>();

    public boolean isAdmin(String username) {
        return admin.contains(username);
    }
}
