package com.sunshanpeng.devops.k8s;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class K8sClient {

    private KubernetesClient client;

    public K8sClient() {
        client = new DefaultKubernetesClient();
    }

    public K8sClient(String kubeConfig) {
        client = DefaultKubernetesClient.fromConfig(kubeConfig);
    }

    public KubernetesClient getClient() {
        return client;
    }

}
