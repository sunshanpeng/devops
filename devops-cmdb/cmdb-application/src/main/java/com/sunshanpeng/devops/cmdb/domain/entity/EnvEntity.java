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
@Table(name = EnvEntity.TABLE_NAME)
@ApiModel(description = "环境信息")
public class EnvEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_env";

    /**
     * 环境编码
     */
    @Column(nullable = false)
    private String envCode;

    /**
     * 环境名称
     */
    @Column(nullable = false)
    private String envName;

    /**
     * 优先级
     */
    @Column(nullable = false)
    private Integer priority;

}
