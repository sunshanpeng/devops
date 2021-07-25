package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "环境集群关联信息")
public class EnvClusterEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_env_cluster";

    /**
     * 环境编码
     */
    private String envCode;

    /**
     * 集群编码
     */
    private String clusterCode;


    @Builder.Default
    protected Boolean isDefault = false;
}
