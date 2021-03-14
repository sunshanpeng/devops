package com.sunshanpeng.devops.module;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@EnableTask
@SpringBootApplication
public class ModuleJob {

    public static void main(String[] args) {
        SpringApplication.run(ModuleJob.class, args);
    }
}
