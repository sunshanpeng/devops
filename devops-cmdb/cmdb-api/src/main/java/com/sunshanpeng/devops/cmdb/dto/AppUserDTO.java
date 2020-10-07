package com.sunshanpeng.devops.cmdb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel("应用负责人")
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "姓名", required = true)
    private String fullName;
}
