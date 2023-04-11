package com.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayFilter implements GlobalFilter {
    Logger logger = LoggerFactory.getLogger(GatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request= exchange.getRequest();
        String headerData=request.getHeaders().getFirst("Auth");
        logger.info("Pre Filter");
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            logger.info("Status from micro service "+exchange.getResponse().getStatusCode());
            logger.info("Post Filter");
        }));
    }
}
