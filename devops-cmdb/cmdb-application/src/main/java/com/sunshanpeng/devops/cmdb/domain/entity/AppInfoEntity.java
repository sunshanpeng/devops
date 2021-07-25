package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "应用信息")
public class AppInfoEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_app_info";

    @NotBlank
    @ApiModelProperty(value = "应用名", required = true)
    private String appName;

    @NotBlank
    @ApiModelProperty(value = "应用描述", required = true)
    private String appDescription;

    @NotBlank
    @ApiModelProperty(value = "应用类型", required = true)
    private String appType;

    @NotBlank
    @ApiModelProperty(value = "应用级别", required = true)
    private String appLevel;

    @NotBlank
    @ApiModelProperty(value = "应用状态", required = true)
    private String status;

    @NotNull
    @ApiModelProperty("应用端口")
    private String ports;

    @NotBlank
    @ApiModelProperty(value = "组织ID", required = true)
    private String orgId;

    @NotBlank
    @ApiModelProperty(value = "组织名称", required = true)
    private String orgName;
}
