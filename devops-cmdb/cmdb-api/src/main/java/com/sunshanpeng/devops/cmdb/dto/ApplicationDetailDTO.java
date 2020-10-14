package com.sunshanpeng.devops.cmdb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("应用详细信息")
public class ApplicationDetailDTO extends ApplicationDTO{
    @ApiModelProperty(value = "代码地址")
    private String codeUrl;

    @ApiModelProperty(value = "制品路径")
    private String artifactPath;

    @ApiModelProperty(value = "存活探针")
    private MonitorDTO liveness;

    @ApiModelProperty(value = "就绪探针")
    private MonitorDTO readiness;

    @ApiModelProperty(value = "Prometheus监控")
    private List<MonitorDTO> prometheus;

}
