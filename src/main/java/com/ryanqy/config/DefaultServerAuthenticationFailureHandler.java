package com.ryanqy.config;

import com.ryanqy.util.JacksonUtils;
import com.ryanqy.util.Result;
import com.ryanqy.util.ResultCodeEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author tong.wu
 * created on 2023/12/28
 */
public class DefaultServerAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(JacksonUtils.writeValueAsString(Result.failed(ResultCodeEnum.UNAUTHORIZED)).getBytes(StandardCharsets.UTF_8));
        return response.writeAndFlushWith(Mono.just(Mono.just(buffer)));
    }

}
