package com.sunshanpeng.devops.member.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sunshanpeng.devops.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel(description = "组织")
@TableName(OrganizationEntity.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationEntity extends BaseEntity {

    public static final String TABLE_NAME = "t_organization";

    @ApiModelProperty(value = "组织名称", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "上级组织", required = true)
    @NotNull
    private Long parentId;
}
