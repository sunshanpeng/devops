package com.sunshanpeng.devops.gateway.filter;

import com.sunshanpeng.devops.gateway.config.AuthWhiteUrlsProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final String AUTH_USERNAME = "username";

    @Resource
    private AuthWhiteUrlsProperties authWhiteUrls;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(skipFilter(exchange, chain)){
            return chain.filter(exchange);
        }
        //从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        //未携带token
        if (StringUtils.isBlank(token)) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            originalResponse.getHeaders().add("Content-Type", "text/html;charset=UTF-8");
            byte[] response = "{\"errorMessage\": \"401 Unauthorized.\"}"
                    .getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }

        //将现在的request，添加当前身份
        ServerHttpRequest httpRequest = exchange.getRequest().mutate()
                .header(AUTH_USERNAME, "sunshanpeng").build();
        ServerWebExchange mutableExchange = exchange.mutate().request(httpRequest).build();
        return chain.filter(mutableExchange);
    }

    private boolean skipFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        //跳过白名单的URL
        if (authWhiteUrls.skip(url)) {
            return true;
        }

        return false;
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
