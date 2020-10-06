package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AppInfoEntity.TABLE_NAME)
@ApiModel(description = "应用信息")
public class AppInfoEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_app_info";

    @Column(nullable = false, unique = true)
    private String appName;

    @Column(nullable = false)
    private String appDescription;

    @Column(nullable = false)
    private String appType;

    @Column(nullable = false)
    private String appLevel;

    @Column(nullable = false)
    private Integer status;

    @Column(name="ports")
    @ElementCollection(targetClass=String.class)
    private List<Integer> ports;

    @Column(nullable = false)
    private String organization;
}
