package com.sunshanpeng.devops.k8s.utils;

import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import com.sunshanpeng.devops.k8s.config.KubeConfig;
import com.sunshanpeng.devops.k8s.dto.ContainerExecDTO;
import com.sunshanpeng.devops.k8s.dto.PodDTO;
import com.sunshanpeng.devops.k8s.event.MessageFuction;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodStatus;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;
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
                    .namespace(metadata.getNamespace())
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

    public static LogWatch log(ContainerExecDTO containerExecDTO, WebSocketSession session) throws IOException {
        KubernetesClient client = KubeConfig.getClient(containerExecDTO.getClusterCode());
        return client.pods().inNamespace(containerExecDTO.getNamespace())
                .withName(containerExecDTO.getPodName()).inContainer(containerExecDTO.getAppName())
                .tailingLines(500).watchLog(getOutputStream(session, true, null));
    }

    public static ExecWatch exec(ContainerExecDTO containerExecDTO, WebSocketSession session,
                                 MessageFuction messageFuction) throws IOException {
        KubernetesClient client = KubeConfig.getClient(containerExecDTO.getClusterCode());
        PipedOutputStream outputStream = new PipedOutputStream();
        return client.pods().inNamespace(containerExecDTO.getNamespace())
                .withName(containerExecDTO.getPodName()).inContainer(containerExecDTO.getAppName())
                .writingInput(outputStream)
                .writingOutput(getOutputStream(session, true, messageFuction))
                .writingError(getOutputStream(session, true, messageFuction))
                .withTTY()
                .exec("env", "TERM=xterm", "COLUMNS=" + (containerExecDTO.getCols() + 1), "LINES=" + containerExecDTO.getRows(),
                        "/bin/sh", "-c", "clear; (bash || ash || sh)");
    }

    private static OutputStream getOutputStream(WebSocketSession session,
                                                boolean isText,
                                                MessageFuction messageFuction) {
        return new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                if (isText) {
                    String message = new String(b, off, len);
                    if (!message.endsWith("\r\n")) {
                        message = message.replaceAll("\n", "\r\n");
                    }
                    session.sendMessage(new TextMessage(message));
                    if (messageFuction != null) {
                        messageFuction.run(session.getId(), message);
                    }
                } else {
                    session.sendMessage(new BinaryMessage(b, off, len, true));
                }
            }
        };
    }

}
