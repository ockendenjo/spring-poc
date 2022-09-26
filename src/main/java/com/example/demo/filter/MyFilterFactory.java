package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MyFilterFactory extends AbstractGatewayFilterFactory<Void> {


    @Override
    public GatewayFilter apply(Void config) {
        return new OrderedGatewayFilter((exchange, chain) -> {

            log.info("pre filter");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                log.info("post filter");
            }));
        }, 0);
    }
}
