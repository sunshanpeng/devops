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
@Table(name = EnvClusterEntity.TABLE_NAME)
@ApiModel(description = "环境集群关联信息")
public class EnvClusterEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_env_cluster";

    /**
     * 环境编码
     */
    @Column(nullable = false)
    private String envCode;

    /**
     * 集群编码
     */
    @Column(nullable = false)
    private String clusterCode;


    @Builder.Default
    @Column(nullable = false)
    protected Boolean isDefault = false;
}
