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
@Table(name = BusinessUnitEntity.TABLE_NAME)
@ApiModel(description = "BU信息/业务线/产品线")
public class BusinessUnitEntity extends BaseEntity {

    public static final String TABLE_NAME = "t_bu";

    /**
     * BU编码
     */
    @Column(nullable = false)
    private String buCode;

    /**
     * BU名称
     */
    @Column(nullable = false)
    private String buName;

    /**
     * 优先级
     */
    @Column(nullable = false)
    private Integer priority;

    /**
     * BU负责人
     */
    @Column(nullable = false)
    private String username;

}
