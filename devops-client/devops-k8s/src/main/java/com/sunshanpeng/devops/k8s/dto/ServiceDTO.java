package com.sunshanpeng.devops.k8s.dto;

import io.fabric8.kubernetes.api.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
public class ServiceDTO {
    private String appName;

    private String namespace;

    @Builder.Default
    private Map<String, String> label = new HashMap<>();

    private Integer port;

    private Boolean isXXL;

    public Service build() {
        label.put("app", appName);
        ServicePort servicePort = new ServicePortBuilder()
                                        .withName(appName)
                                        .withPort(port)
                                        .withProtocol("TCP")
                                        .withTargetPort(new IntOrString(port))
                                    .build();
        return new ServiceBuilder()
                .withNewMetadata()
                    .withName(appName)
                    .withNamespace(namespace)
                    .withLabels(label)
                    .endMetadata()
                .withNewSpec()
                    .withSelector(label)
                    .withPorts(servicePort)
                .endSpec()
                .build();
    }
}
