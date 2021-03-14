package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AppUserEntity.TABLE_NAME)
@ApiModel(description = "应用负责人信息")
public class AppUserEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_app_user";

    @NotBlank
    @ApiModelProperty(value = "应用名", required = true)
    @Column(nullable = false, columnDefinition = "varchar(64) not null default '' comment '应用名'")
    private String appName;

    @NotBlank
    @ApiModelProperty(value = "负责人类型", required = true)
    @Column(nullable = false, columnDefinition = "varchar(64) not null default '' comment '负责人类型'")
    private String AppUserType;

    @NotBlank
    @ApiModelProperty(value = "用户名", required = true)
    @Column(nullable = false, columnDefinition = "varchar(255) not null default '' comment '用户名'")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "全名", required = true)
    @Column(nullable = false, columnDefinition = "varchar(255) not null default '' comment '用户名'")
    private String fullName;
}
