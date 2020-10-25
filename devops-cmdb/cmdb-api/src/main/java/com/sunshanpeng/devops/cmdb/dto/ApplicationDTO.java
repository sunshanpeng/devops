package com.sunshanpeng.devops.cmdb.dto;

import com.sunshanpeng.devops.common.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("应用信息")
public class ApplicationDTO extends BaseDTO {

    @ApiModelProperty(value = "应用名称", required = true)
    private String appName;

    @ApiModelProperty(value = "应用描述", required = true)
    private String appDescription;

    @ApiModelProperty(value = "应用类型", required = true)
    private String appType;

    @ApiModelProperty(value = "应用等级", required = true)
    private String appLevel;

    @ApiModelProperty(value = "应用状态", required = true)
    private String status;

    @ApiModelProperty(value = "组织ID", required = true)
    private String orgId;

    @ApiModelProperty(value = "组织名称", required = true)
    private String orgName;

    @ApiModelProperty(value = "应用端口")
    private String ports;

    @ApiModelProperty(value = "主要负责人", required = true)
    private AppUserDTO primary;

    @ApiModelProperty(value = "次要负责人", required = true)
    private List<AppUserDTO> secondary;

    @ApiModelProperty(value = "质量负责人", required = true)
    private List<AppUserDTO> qa;

    public void setPrimary(String username, String fullName) {
        this.primary = new AppUserDTO(username, fullName);
    }
    public Integer getWebPort() {
        if (!StringUtils.isEmpty(ports)) {
            String[] split = ports.split(",");
            return Integer.valueOf(split[0]);
        }
        return 8080;
    }
}
