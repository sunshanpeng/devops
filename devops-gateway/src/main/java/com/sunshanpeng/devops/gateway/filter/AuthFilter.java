package com.sunshanpeng.devops.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sunshanpeng.devops.gateway.config.AuthWhiteUrlsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Optional;

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    static final String AUTH_USERNAME = "username";

    @Value("${auth.jwt.secret:123456}")
    private String secretKey;

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
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"errorMessage\": \"unauthorized.\"}"
                    .getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }
        //校验token并取出username
        Optional<String> usernameOptional = verifyToken(token);
        //无效token
        if (!usernameOptional.isPresent()) {
            ServerHttpResponse originalResponse = exchange.getResponse();
            originalResponse.setStatusCode(HttpStatus.FORBIDDEN);
            originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            byte[] response = "{\"errorMessage\": \"invalid token.\"}"
                    .getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
            return originalResponse.writeWith(Flux.just(buffer));
        }

        //把username放到请求头中
        ServerHttpRequest httpRequest = exchange.getRequest().mutate()
                .header(AUTH_USERNAME, usernameOptional.get()).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(httpRequest).build();
        return chain.filter(mutableExchange);
    }

    private boolean skipFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        //跳过白名单的URL
        if (authWhiteUrls.skip(url)) {
            return true;
        }
        //跳过静态资源
        if (url.endsWith(".html") || url.endsWith(".js")
                    || url.endsWith(".css")) {
            return true;
        }
        //跳过swagger接口（线上不能跳过）
        if (url.contains("/swagger-resources") || url.contains("/v2/api-docs")) {
            return true;
        }
        return false;
    }

    private Optional<String> verifyToken(String token) {
        return verifyJWT(token);
    }

    /**
     * JWT验证
     * @param token
     * @return userName
     */
    private Optional<String> verifyJWT(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("MING")
                .build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.warn(String.format("jwt verify error, token:[%s]", token), e);
            return Optional.empty();
        }
        return Optional.ofNullable(jwt.getClaim("username").asString());
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
