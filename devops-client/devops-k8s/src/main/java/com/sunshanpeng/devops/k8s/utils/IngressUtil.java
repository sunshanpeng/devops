package com.sunshanpeng.devops.k8s.utils;

import com.sunshanpeng.devops.k8s.config.KubeConfig;
import com.sunshanpeng.devops.k8s.dto.IngressRuleDTO;
import io.fabric8.kubernetes.api.model.networking.v1beta1.Ingress;
import io.fabric8.kubernetes.api.model.networking.v1beta1.IngressRule;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class IngressUtil {

    public static void addRule(String clusterCode, IngressRuleDTO ingressRuleDTO) throws Exception {
        KubernetesClient client = KubeConfig.getClient(clusterCode);
        Ingress ingress = client.network().ingress().inNamespace(ingressRuleDTO.getNamespace())
                .withName(ingressRuleDTO.getIngressName()).get();
        if (ingress == null) {
            log.warn("cluster:{} ingress:{} is not found", clusterCode, ingressRuleDTO.getIngressName());
            return;
        }
        List<IngressRule> rules = ingress.getSpec().getRules();
        IngressRule rule = ingressRuleDTO.build();
        if (rules.contains(rule)) {
            log.info("host {} is exist", ingressRuleDTO.getHost());
            return;
        }
        rules.add(rule);
        client.network().ingress().inNamespace(ingressRuleDTO.getNamespace())
                .withName(ingressRuleDTO.getIngressName()).createOrReplace(ingress);
    }
}
