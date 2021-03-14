package com.sunshanpeng.devops.module.dto;

import com.sunshanpeng.devops.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("module")
public class ModuleDTO extends BaseDTO {
    private String name;
}
