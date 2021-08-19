package com.sunshanpeng.devops.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;
}
