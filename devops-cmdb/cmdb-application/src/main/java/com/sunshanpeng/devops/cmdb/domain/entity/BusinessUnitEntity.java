package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "BU信息/业务线/产品线")
public class BusinessUnitEntity extends BaseEntity {

    public static final String TABLE_NAME = "t_bu";

    /**
     * BU编码
     */
    private String buCode;

    /**
     * BU名称
     */
    private String buName;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * BU负责人
     */
    private String username;

}
