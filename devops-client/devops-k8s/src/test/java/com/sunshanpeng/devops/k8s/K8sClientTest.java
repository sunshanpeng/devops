package com.sunshanpeng.devops.k8s;

import io.fabric8.kubernetes.api.model.NamespaceList;
import org.junit.Test;

public class K8sClientTest {

    @Test
    public void getNamespace() {
        NamespaceList list = new K8sClient().getClient().namespaces().list();
        list.getItems().forEach(ns -> {
            System.out.println(ns.getMetadata().getName());
        });
    }

}
