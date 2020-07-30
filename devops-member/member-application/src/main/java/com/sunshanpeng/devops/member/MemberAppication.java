package com.sunshanpeng.devops.member;

import com.sunshanpeng.devops.common.base.BaseJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class)
@SpringBootApplication
public class MemberAppication {

    public static void main(String[] args) {
        SpringApplication.run(MemberAppication.class, args);
    }
}
