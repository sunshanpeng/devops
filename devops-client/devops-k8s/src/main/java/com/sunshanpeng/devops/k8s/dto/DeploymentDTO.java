package com.sunshanpeng.devops.k8s.dto;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.utils.Serialization;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class DeploymentDTO {

    @NotBlank
    private String appName;

    @NotBlank
    private String namespace;

    @NotNull
    private Integer replicas;

    @NotBlank
    private String template;

    public Deployment generate() {
        Map<String, String> map = new HashMap<>();
        map.put("app_name", appName);
        map.put("namespace", namespace);
        map.put("replicas", String.valueOf(replicas));
        return Serialization.unmarshal(template, Deployment.class, map);
    }
}
