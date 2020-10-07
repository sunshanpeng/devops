package com.sunshanpeng.devops.cmdb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AppUserDTO {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "姓名", required = true)
    private String fullName;
}
