package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    @Column(nullable = false)
    private String appName;

    @Column(nullable = false)
    private String AppUserType;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String fullName;
}
