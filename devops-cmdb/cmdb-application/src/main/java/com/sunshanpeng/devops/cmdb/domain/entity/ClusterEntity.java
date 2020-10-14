package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.cmdb.enums.ClusterTypeEnum;
import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ClusterEntity.TABLE_NAME)
@ApiModel(description = "集群信息")
public class ClusterEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_cluster";

    /**
     * 集群编码
     */
    @Column(nullable = false)
    @NotBlank
    private String clusterCode;

    /**
     * 集群名称
     */
    @Column(nullable = false)
    @NotBlank
    private String clusterName;

    /**
     * 集群类型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClusterTypeEnum clusterType;

    /**
     * 优先级
     */
    @Column(nullable = false)
    private Integer priority;
}
