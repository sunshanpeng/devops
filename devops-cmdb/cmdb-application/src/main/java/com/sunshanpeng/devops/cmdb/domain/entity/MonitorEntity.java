package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "监控信息")
public class MonitorEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_monitor";

    private String appName;

    private String monitorType;

    private Integer monitorPort;

    private String monitorPath;

}
