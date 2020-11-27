package com.sunshanpeng.devops.k8s.websocket;

import com.sunshanpeng.devops.k8s.dto.ContainerExecDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
public class ContainerExecHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     * 握手前
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        MultiValueMap<String, String> queryParams =
                UriComponentsBuilder.fromUriString(request.getURI().toString()).build().getQueryParams();
        ContainerExecDTO containerExecDTO = ContainerExecDTO.builder().rows(queryParams.getFirst("rows"))
                .cols(queryParams.getFirst("cols"))
                .clusterCode(queryParams.getFirst("clusterCode"))
                .podName(queryParams.getFirst("podName"))
                .namespace(queryParams.getFirst("namespace"))
                .username(queryParams.getFirst("username"))
                .build();
        log.info("containerExecDTO:{}", containerExecDTO);
        attributes.put("param", containerExecDTO);
        return true;
    }

    /**
     * 握手后
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("握手完成");
    }
}
