package com.sunshanpeng.devops.k8s.utils;

import com.sunshanpeng.devops.k8s.config.KubeConfig;
import com.sunshanpeng.devops.k8s.dto.DeploymentDTO;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class DeploymentUtil {

    public static Deployment get(@NotBlank String clusterCode, @NotBlank String namespace, @NotBlank String appName) throws IOException {
        KubernetesClient client = KubeConfig.getClient(clusterCode);
        return client.apps().deployments().inNamespace(namespace).withName(appName).get();
    }

    public static Deployment create(@NotBlank String clusterCode, @Validated @NotNull DeploymentDTO deploymentDTO) throws IOException {
        KubernetesClient client = KubeConfig.getClient(clusterCode);
        Deployment deployment = get(clusterCode, deploymentDTO.getNamespace(), deploymentDTO.getAppName());
        if (deployment != null) {
            log.info("cluster:{}, deployment:{} is exist", clusterCode, deploymentDTO.getAppName());
            return deployment;
        }
        return client.apps().deployments().inNamespace(deploymentDTO.getNamespace())
                .create(deploymentDTO.generate());
    }

    public static void updateImage(@NotBlank String clusterCode, @NotBlank String namespace,
                                 @NotBlank String appName, @NotBlank String image) throws IOException {
        KubernetesClient client = KubeConfig.getClient(clusterCode);
        Deployment deployment = client.apps().deployments().inNamespace(namespace).withName(appName).get();
        Assert.notNull(deployment, String.format("deployment of %s/%s is not exist", namespace, appName));

        deployment.getSpec().getTemplate().getSpec().getContainers()
                .stream().filter(container -> appName.equals(container.getName()))
                .peek(container -> container.setImage(image)).collect(Collectors.toList());
        client.apps().deployments().inNamespace(namespace).createOrReplace(deployment);
    }


}
