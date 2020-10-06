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
@Table(name = CiInfoEntity.TABLE_NAME)
@ApiModel(description = "持续集成信息")
public class CiInfoEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_ci_info";

    @Column(nullable = false, unique = true)
    private String appName;

    @Column(nullable = false)
    private String codeUrl;

    @Column(nullable = false)
    private String artifactPath;
}
