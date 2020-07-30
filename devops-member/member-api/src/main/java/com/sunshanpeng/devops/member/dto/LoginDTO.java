package com.sunshanpeng.devops.member.dto;

import com.sunshanpeng.devops.common.base.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO extends BaseDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
