package com.sunshanpeng.devops.jumpserver.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssertUser {
    private String asset;
    private String username;
    private String password;
}
