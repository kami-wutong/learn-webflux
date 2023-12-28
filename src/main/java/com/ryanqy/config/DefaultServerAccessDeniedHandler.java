package com.ryanqy.config;

import com.ryanqy.util.JacksonUtils;
import com.ryanqy.util.Result;
import com.ryanqy.util.ResultCodeEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author tong.wu
 * created on 2023/12/28
 */
public class DefaultServerAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(JacksonUtils.writeValueAsString(Result.failed(ResultCodeEnum.FORBIDDEN)).getBytes(StandardCharsets.UTF_8));
        return response.writeAndFlushWith(Mono.just(Mono.just(buffer)));
    }

}
