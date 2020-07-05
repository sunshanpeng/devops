package com.sunshanpeng.devops.member.dto;

import com.sunshanepng.devops.common.base.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel("用户分页查询类")
public class MemberPageQueryDTO extends BasePageRequest {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机")
    private String phone;
}
