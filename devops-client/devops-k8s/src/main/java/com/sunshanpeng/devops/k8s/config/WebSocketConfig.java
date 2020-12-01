package com.sunshanpeng.devops.k8s.config;

import com.sunshanpeng.devops.common.core.thread.BaseThreadFactory;
import com.sunshanpeng.devops.common.core.thread.ThreadPoolProperties;
import com.sunshanpeng.devops.k8s.websocket.ContainerExecHandshakeInterceptor;
import com.sunshanpeng.devops.k8s.websocket.ContainerExecWSHandler;
import com.sunshanpeng.devops.k8s.websocket.ContainerLogWSHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.concurrent.*;

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

    @Bean
    public ContainerLogWSHandler containerlogWSHandler() {
        return new ContainerLogWSHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //  ws://localhost:8080/exec
        registry.addHandler(containerExecWSHandler(), "exec")
                .addHandler(containerlogWSHandler(), "log")
                .addInterceptors(new ContainerExecHandshakeInterceptor())
                .setAllowedOrigins("*");
    }
    /**
     * 容器terminal exec threadpool
     *
     * @return
     */
    @Bean(name = "execExecutorService")
    public ExecutorService getExecExecutorService() {
        return getExecutorService(getContainerExecThreadPoolProperties(), new BaseThreadFactory("k8s-exec"));
    }

    /**
     * 容器log threadpool
     *
     * @return
     */
    @Bean(name = "logExecutorService")
    public ExecutorService getLogExecutorService() {
        return getExecutorService(getContainerExecThreadPoolProperties(), new BaseThreadFactory("k8s-exec"));
    }
    /**
     * 容器terminal exec thread pool 配置
     *
     * @return
     */
    @Bean(name = "execThreadPoolProperties")
    @ConfigurationProperties(prefix = "thread-pool.exec")
    public ThreadPoolProperties getContainerExecThreadPoolProperties() {
        return new ThreadPoolProperties();
    }

    /**
     * 容器terminal exec thread pool 配置
     *
     * @return
     */
    @Bean(name = "logThreadPoolProperties")
    @ConfigurationProperties(prefix = "thread-pool.log")
    public ThreadPoolProperties getContainerLogThreadPoolProperties() {
        return new ThreadPoolProperties();
    }

    public static ExecutorService getExecutorService(ThreadPoolProperties poolProperties, ThreadFactory factory) {
        return getExecutorService(poolProperties, factory, new ThreadPoolExecutor.AbortPolicy());

    }

    public static ExecutorService getExecutorService(ThreadPoolProperties poolProperties, ThreadFactory factory, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(poolProperties.getCorePoolSize(), poolProperties.getMaxPoolSize(),
                poolProperties.getKeepAliveSeconds(), TimeUnit.SECONDS, new ArrayBlockingQueue<>(poolProperties.getMaxQueueSize()), factory, handler);

    }
}
