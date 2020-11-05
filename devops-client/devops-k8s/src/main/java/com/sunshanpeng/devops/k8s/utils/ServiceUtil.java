package com.sunshanpeng.devops.k8s.utils;


import com.sunshanpeng.devops.k8s.config.KubeConfig;
import com.sunshanpeng.devops.k8s.dto.ServiceDTO;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ServiceUtil {

    public static Service get(String clusterCode, String namespace, String name) throws IOException {
        KubernetesClient client = KubeConfig.getClient(clusterCode);
        return client.services().inNamespace(namespace).withName(name).get();
    }

    public static Service create(String clusterCode, ServiceDTO serviceDTO) throws IOException {
        Service service = get(clusterCode, serviceDTO.getNamespace(), serviceDTO.getAppName());
        if (service != null) {
            log.info("cluster:{}, service:{} is exist", clusterCode, serviceDTO.getAppName());
            return service;
        }
        KubernetesClient client = KubeConfig.getClient(clusterCode);
        return client.services().inNamespace(serviceDTO.getNamespace())
                .create(serviceDTO.build());
    }
}
