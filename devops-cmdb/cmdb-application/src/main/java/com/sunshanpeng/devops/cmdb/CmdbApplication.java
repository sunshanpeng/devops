package com.sunshanpeng.devops.cmdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(com.sunshanpeng.devops.k8s.config.WebSocketConfig.class)
public class CmdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmdbApplication.class, args);
    }

}
