package com.sunshanpeng.devops.k8s;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.io.IOException;

public class K8sClient {

    private KubernetesClient client;

    public K8sClient() {
        client = new DefaultKubernetesClient();
    }

    public K8sClient(String kubeconfigContents) throws IOException {
        client = new DefaultKubernetesClient(Config.fromKubeconfig(kubeconfigContents));
    }

    public KubernetesClient getClient() {
        return client;
    }

}
