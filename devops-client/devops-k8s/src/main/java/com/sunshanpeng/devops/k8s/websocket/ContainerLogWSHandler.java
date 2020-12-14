package com.sunshanpeng.devops.k8s.websocket;

import com.sunshanpeng.devops.k8s.dto.ContainerExecDTO;
import com.sunshanpeng.devops.k8s.utils.PodUtil;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Optional;

@Slf4j
public class ContainerLogWSHandler extends TextWebSocketHandler {


    /**
     * 建立连接
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        LogWatch logWatch = PodUtil.log((ContainerExecDTO) session.getAttributes().get("param"), session);
        session.getAttributes().put("logWatch", logWatch);
    }

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if ("heartbeat".equals(message.getPayload())) {
            return;
        }
        //
    }

    /**
     * 关闭连接
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LogWatch logWatch = (LogWatch) session.getAttributes().get("logWatch");
        Optional.ofNullable(logWatch).ifPresent(LogWatch::close);
    }
}
