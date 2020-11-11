package com.sunshanpeng.devops.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@ApiModel("用户基本信息")
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMemberDTO {

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "姓名", required = true)
    private String fullName;

    @ApiModelProperty(value = "权限", hidden = true)
    private Set<String> roles = new HashSet<>();

    public void addRole(String role) {
        roles.add(role);
    }
}
