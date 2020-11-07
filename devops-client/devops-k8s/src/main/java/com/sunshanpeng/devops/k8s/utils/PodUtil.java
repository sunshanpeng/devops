package com.sunshanpeng.devops.k8s.utils;

import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import com.sunshanpeng.devops.k8s.config.KubeConfig;
import com.sunshanpeng.devops.k8s.dto.PodDTO;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodStatus;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class PodUtil {

    public static List<PodDTO> get(@NotBlank String clusterCode, @NotBlank String namespace, @NotBlank String appName) throws IOException {
        KubernetesClient client = KubeConfig.getClient(clusterCode);
        List<Pod> app = client.pods().inNamespace(namespace).withLabel("app", appName).list().getItems();
        return app.stream().map(pod -> {
            PodStatus status = pod.getStatus();
            ObjectMeta metadata = pod.getMetadata();
            Optional<ContainerStatus> containerStatusOptional = status.getContainerStatuses().stream()
                    .filter(containerStatus -> appName.equals(containerStatus.getName()))
                    .findFirst();
            ValueLabelDTO valueLabelDTO = containerStatusOptional.map(ContainerStatus::getState).map(containerState -> {
                if (containerState.getWaiting() != null) {
                    return new ValueLabelDTO(containerState.getWaiting().getReason(),
                            containerState.getWaiting().getMessage());
                }
                if (containerState.getTerminated() != null) {
                    return new ValueLabelDTO(containerState.getTerminated().getReason(),
                            containerState.getTerminated().getMessage());
                }
                if (containerState.getRunning() != null) {
                    if (containerStatusOptional.map(ContainerStatus::getReady).orElse(false)) {
                        return new ValueLabelDTO("Running", "");
                    }
                    return new ValueLabelDTO("Unhealth", "");
                }
                log.warn("pod status is unknown, info: {}", pod.toString());
                return new ValueLabelDTO("Unknown", status.getMessage());
            }).orElse(new ValueLabelDTO("ERROR", status.getMessage()));
            return PodDTO.builder().podName(metadata.getName())
                    .podIp(status.getPodIP())
                    .hostIp(status.getHostIP())
                    .createTime(metadata.getCreationTimestamp())
                    .podStatus(valueLabelDTO.getValue().toString())
                    .statusDesc(valueLabelDTO.getLabel().toString())
                    .restartCount(containerStatusOptional.map(ContainerStatus::getRestartCount).orElse(0)
                    ).build();
        }).collect(Collectors.toList());
    }

    public static void delete(@NotBlank String clusterCode, @NotBlank String namespace, @NotBlank String podName) throws IOException {
        KubernetesClient client = KubeConfig.getClient(clusterCode);
        client.pods().inNamespace(namespace).withName(podName).delete();
    }

}