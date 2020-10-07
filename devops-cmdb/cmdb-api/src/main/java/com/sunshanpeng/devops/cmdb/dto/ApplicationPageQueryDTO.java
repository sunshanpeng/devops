package com.sunshanpeng.devops.cmdb.dto;

import com.sunshanpeng.devops.common.base.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel("应用分页查询")
public class ApplicationPageQueryDTO extends BasePageRequest {
    @ApiModelProperty(value = "应用名称")
    private String appName;
}
