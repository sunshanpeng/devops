package com.sunshanpeng.devops.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sunshanpeng.devops.member.domain.dao")
public class MemberAppication {

    public static void main(String[] args) {
        SpringApplication.run(MemberAppication.class, args);
    }
}
