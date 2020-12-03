package com.sunshanpeng.devops.k8s.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PodDTO {

    private String podName;

    private String namespace;

    private String podIp;

    private String hostIp;

    private String podStatus;

    private String statusDesc;

    private Integer restartCount;

    private String createTime;
}
