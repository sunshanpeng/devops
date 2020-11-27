package com.sunshanpeng.devops.k8s.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContainerExecDTO {
    /**
     * 应用名
     */
    private String appName;
    /**
     * pod名
     */
    private String podName;
    /**
     * 集群编码
     */
    private String clusterCode;
    /**
     * 环境
     */
    private String envCode;

    private String username;

    private String namespace;

    private String cols;

    private String rows;
}
