package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "持续集成信息")
public class CiInfoEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_ci_info";

    private String appName;

    private String codeUrl;

    private String artifactPath;
}
