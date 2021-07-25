package com.sunshanpeng.devops.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;
}
