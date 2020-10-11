package com.sunshanpeng.devops.member.dto;

import com.sunshanpeng.devops.common.base.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel("组织结构查询类")
public class OrgPageQueryDTO extends BasePageRequest {
    @ApiModelProperty(value = "组织名称")
    private String name;

    @ApiModelProperty(value = "上级组织")
    private String parentId;
}
