package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AppInfoEntity.TABLE_NAME)
@ApiModel(description = "应用信息")
public class AppInfoEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_app_info";

    @NotBlank
    @ApiModelProperty(value = "应用名", required = true)
    @Column(nullable = false, unique = true, columnDefinition = "varchar(64) not null default '' comment '应用名'")
    private String appName;

    @NotBlank
    @ApiModelProperty(value = "应用描述", required = true)
    @Column(nullable = false, columnDefinition = "varchar(255) not null default '' comment '应用描述'")
    private String appDescription;

    @NotBlank
    @ApiModelProperty(value = "应用类型", required = true)
    @Column(nullable = false, columnDefinition = "varchar(64) not null default '' comment '应用类型'")
    private String appType;

    @NotBlank
    @ApiModelProperty(value = "应用级别", required = true)
    @Column(nullable = false, columnDefinition = "varchar(64) not null default '' comment '应用级别'")
    private String appLevel;

    @NotBlank
    @ApiModelProperty(value = "应用状态", required = true)
    @Column(nullable = false, columnDefinition = "varchar(64) not null default '' comment '应用状态'")
    private String status;

    @NotNull
    @ApiModelProperty("应用端口")
    @Column(nullable = false, columnDefinition = "varchar(64) not null default '' comment '应用端口'")
    private String ports;

    @NotBlank
    @ApiModelProperty(value = "组织ID", required = true)
    @Column(nullable = false, columnDefinition = "varchar(64) not null default '' comment '组织ID'")
    private String orgId;

    @NotBlank
    @ApiModelProperty(value = "组织名称", required = true)
    @Column(nullable = false, columnDefinition = "varchar(64) not null default '' comment '组织名称'")
    private String orgName;
}
