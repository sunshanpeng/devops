package com.sunshanpeng.devops.k8s.websocket;

import com.sunshanpeng.devops.k8s.dto.ContainerExecDTO;
import com.sunshanpeng.devops.k8s.utils.PodUtil;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@Slf4j
public class ContainerExecWSHandler extends TextWebSocketHandler {
    /**
     * 建立连接
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        ExecWatch exec = PodUtil.exec((ContainerExecDTO) session.getAttributes().get("param"), session);
        session.getAttributes().put("exec", exec);
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
        // 获得客户端传来的消息
        ExecWatch exec = (ExecWatch) session.getAttributes().get("exec");
        OutputStream input = exec.getInput();
        input.write(message.asBytes());
        input.flush();
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
        ExecWatch exec = (ExecWatch) session.getAttributes().get("exec");
        OutputStream input = exec.getInput();
        Optional.ofNullable(input).ifPresent(in -> {
            try {
                input.write("exit\r\n".getBytes());
                input.flush();
                input.close();
            } catch (IOException e) {
                log.error("close send message error", e);
            }
        });
    }
}
