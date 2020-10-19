package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = MetaConfigEntity.TABLE_NAME,
        uniqueConstraints = @UniqueConstraint(columnNames = {"c_group", "c_key"}))
@ApiModel(description = "元数据配置信息")
public class MetaConfigEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_meta_config";

    @Column(nullable = false, name = "c_group")
    private String group;

    @Column(nullable = false, name = "c_key")
    private String key;

    @Lob
    @Column(nullable = false, name = "c_value")
    private String value;

    @Column(nullable = false, name = "c_description")
    private String description;

}
