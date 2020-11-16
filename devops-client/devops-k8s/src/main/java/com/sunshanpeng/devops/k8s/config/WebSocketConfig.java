package com.sunshanpeng.devops.k8s.config;

import com.sunshanpeng.devops.k8s.websocket.ContainerExecHandshakeInterceptor;
import com.sunshanpeng.devops.k8s.websocket.ContainerExecWSHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(ApplicationContext context) {
        return new ServerEndpointExporter();
    }

    @Bean
    public ContainerExecWSHandler containerExecWSHandler() {
        return new ContainerExecWSHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //  ws://localhost:8080/exec
        registry.addHandler(containerExecWSHandler(), "exec")
                .addInterceptors(new ContainerExecHandshakeInterceptor())
                .setAllowedOrigins("*");
    }

}
