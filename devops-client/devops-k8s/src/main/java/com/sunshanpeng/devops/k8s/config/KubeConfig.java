package com.sunshanpeng.devops.k8s.config;

import com.sunshanpeng.devops.k8s.K8sClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KubeConfig {

    private static Map<String, KubernetesClient> K8S_CLIENT = new ConcurrentHashMap<>();

    private static Map<String, String> CONFIG = new ConcurrentHashMap<>();

    public static void addConfig(String clusterCode, String kubeConfig) {
        CONFIG.put(clusterCode, kubeConfig);
    }

    public static KubernetesClient getClient(String clusterCode) throws IOException {
        KubernetesClient kubernetesClient = K8S_CLIENT.get(clusterCode);
        if (kubernetesClient != null) {
            return kubernetesClient;
        }
        kubernetesClient = new K8sClient(CONFIG.get(clusterCode)).getClient();
        K8S_CLIENT.put(clusterCode, kubernetesClient);
        return kubernetesClient;
    }
}
