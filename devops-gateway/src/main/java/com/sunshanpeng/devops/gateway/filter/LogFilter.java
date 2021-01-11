package com.sunshanpeng.devops.gateway.filter;

import com.sunshanpeng.devops.gateway.config.RecordWhiteUrlsProperties;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

@Slf4j
@Component
public class LogFilter implements GlobalFilter, Ordered {

    @Resource
    private RecordWhiteUrlsProperties recordWhiteUrlsProperties;

    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";
    private static final String REQUEST_BODY = "requestBody";
    private static final String RESPONSE_BODY = "responseBody";
    private static final String ROUTE_KEY = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
        ServerHttpRequest request = exchange.getRequest();
        String method = request.getMethodValue();
        if (!"GET".equals(method)) {
            return DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        String bodyString = new String(bytes, StandardCharsets.UTF_8);
                        exchange.getAttributes().put(REQUEST_BODY, bodyString);
                        DataBufferUtils.release(dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                            DataBuffer buffer = exchange.getResponse().bufferFactory()
                                    .wrap(bytes);
                            return Mono.just(buffer);
                        });

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        return chain.filter(exchange.mutate()
                                .request(mutatedRequest)
                                .response(response(exchange))
                                .build());
                    });
        }

        return chain.filter(exchange.mutate()
                .response(response(exchange))
                .build());
    }


    private ServerHttpResponse response(ServerWebExchange exchange) {
        return new ServerHttpResponseDecorator(exchange.getResponse()) {
            @Override
            @SuppressWarnings("unchecked")
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {

                Class inClass = String.class;
                Class outClass = String.class;

                String originalResponseContentType = exchange
                        .getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.add(HttpHeaders.CONTENT_TYPE,
                        originalResponseContentType);

                ClientResponse clientResponse = ClientResponse
                        .create(Objects.requireNonNull(exchange.getResponse().getStatusCode()))
                        .headers(headers -> headers.putAll(httpHeaders))
                        .body(Flux.from(body)).build();

                Mono modifiedBody = clientResponse.bodyToMono(inClass)
                        .flatMap(originalBody -> {
                            String contentType = exchange.getResponse().getHeaders().getFirst("Content-Type");
                            if (contentType != null && contentType.contains("application/json")) {
                                exchange.getAttributes().put(RESPONSE_BODY, originalBody.toString());
                                output(exchange);
                            } else {
                                ServerHttpRequest request = exchange.getRequest();
                                log.warn("uri:{}, method:{}, contentType:{}", request.getURI().getRawPath(), request.getMethodValue(), contentType);
                            }
                            return Mono.just(originalBody);
                        });

                BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody,
                        outClass);
                CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(
                        exchange, exchange.getResponse().getHeaders());
                return bodyInserter.insert(outputMessage, new BodyInserterContext())
                        .then(Mono.defer(() -> {
                            Flux<DataBuffer> messageBody = outputMessage.getBody();
                            HttpHeaders headers = getDelegate().getHeaders();
                            if (!headers.containsKey(HttpHeaders.TRANSFER_ENCODING)) {
                                messageBody = messageBody.doOnNext(data -> headers
                                        .setContentLength(data.readableByteCount()));
                            }
                            return getDelegate().writeWith(messageBody);
                        }));
            }

            @Override
            public Mono<Void> writeAndFlushWith(
                    Publisher<? extends Publisher<? extends DataBuffer>> body) {
                return writeWith(Flux.from(body).flatMapSequential(p -> p));
            }
        };
    }

    private void output(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
        AtomicReference<String> queryParams = new AtomicReference<>(request.getQueryParams().toString());
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String username = request.getHeaders().getFirst(AuthFilter.AUTH_USERNAME);
        if (startTime != null) {
            long endTime = System.currentTimeMillis();
            long requestTime = endTime - startTime;
            String method = request.getMethodValue();
            String rawPath = request.getURI().getRawPath();
            Route route = exchange.getAttribute(ROUTE_KEY);
            String referer = request.getHeaders().getFirst("Referer");
            AtomicReference<String> requestBody = new AtomicReference<>(exchange.getAttribute(REQUEST_BODY));
            AtomicReference<String> responseBody = new AtomicReference<>(exchange.getAttribute(RESPONSE_BODY));
            if (recordWhiteUrlsProperties.skip(rawPath)) {
                queryParams.set("");
                requestBody.set("");
                responseBody.set("");
            }
            String ip = remoteAddress.getAddress().getHostAddress();
            Optional.ofNullable(route).ifPresent(r -> {
            });
        }
    }


    @Override
    public int getOrder() {
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
    }
}
