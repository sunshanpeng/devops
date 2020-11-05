package com.sunshanpeng.devops.k8s.dto;

import io.fabric8.kubernetes.api.model.networking.v1beta1.IngressRule;
import io.fabric8.kubernetes.api.model.networking.v1beta1.IngressRuleBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngressRuleDTO {
    private String appName;

    private String namespace;

    private String ingressName;

    private String host;

    private String path;

    private Integer port;

    public IngressRule build() {
        return new IngressRuleBuilder().withHost(host)
                .withNewHttp().addNewPath().withPath(path)
                .withNewBackend().withServiceName(appName).withNewServicePort(port)
                .endBackend().endPath().endHttp().build();
    }

}
