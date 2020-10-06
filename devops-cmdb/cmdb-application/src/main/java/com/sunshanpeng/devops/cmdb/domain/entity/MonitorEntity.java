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
@Table(name = MonitorEntity.TABLE_NAME)
@ApiModel(description = "监控信息")
public class MonitorEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_monitor";

    @Column(nullable = false)
    private String appName;

    @Column(nullable = false)
    private String monitorType;

    @Column(nullable = false)
    private String monitorPort;

    @Column(nullable = false)
    private String monitorPath;

}
