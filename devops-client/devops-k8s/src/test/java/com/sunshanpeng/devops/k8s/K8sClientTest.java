package com.sunshanpeng.devops.k8s;

import io.fabric8.kubernetes.api.model.HTTPGetAction;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.Probe;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.junit.Test;

public class K8sClientTest {

    @Test
    public void getNamespace() {
        KubernetesClient client = new K8sClient().getClient();
        NamespaceList list = client.namespaces().list();
        list.getItems().forEach(ns -> {
            System.out.println(ns.getMetadata().getName());
        });
    }

    @Test
    public void getDeployment() {
        KubernetesClient client = new K8sClient().getClient();
        DeploymentList deploymentList = client.apps().deployments().list();
        deploymentList.getItems().forEach(deployment -> {
            System.out.println(deployment.getMetadata().getName());
            deployment.getSpec().getTemplate().getSpec().getContainers().forEach(container -> {
                Probe readinessProbe = container.getReadinessProbe();
                if (readinessProbe == null) {
                    readinessProbe = new Probe();
                    HTTPGetAction httpGetAction = new HTTPGetAction();
                    httpGetAction.setPort(new IntOrString(8080));
                    httpGetAction.setPath("/actuator/health");
                    readinessProbe.setHttpGet(httpGetAction);
                    container.setReadinessProbe(readinessProbe);
                }
            });
            System.out.println(deployment);
        });
    }

}
