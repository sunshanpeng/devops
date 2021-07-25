package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "应用负责人信息")
public class AppUserEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_app_user";

    @NotBlank
    @ApiModelProperty(value = "应用名", required = true)
    private String appName;

    @NotBlank
    @ApiModelProperty(value = "负责人类型", required = true)
    private String AppUserType;

    @NotBlank
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(value = "全名", required = true)
    private String fullName;
}
