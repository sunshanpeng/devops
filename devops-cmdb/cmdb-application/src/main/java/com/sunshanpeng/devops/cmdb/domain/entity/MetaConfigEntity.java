package com.sunshanpeng.devops.cmdb.domain.entity;

import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "元数据配置信息")
public class MetaConfigEntity extends BaseEntity {
    public static final String TABLE_NAME = "t_meta_config";

    private String group;

    private String key;

    private String value;

    private String description;

}
