package com.sunshanpeng.devops.cmdb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("监控信息")
public class MonitorDTO {

    @ApiModelProperty(value = "监控端口号", required = true)
    private Integer monitorPort;

    @ApiModelProperty(value = "监控路径", required = true)
    private String monitorPath;
}
