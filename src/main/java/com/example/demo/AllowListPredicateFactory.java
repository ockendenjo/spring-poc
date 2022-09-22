package com.example.demo;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

public class AllowListPredicateFactory extends AbstractRoutePredicateFactory<Void> {

    private static final String REGEX = "^/users/\\d+$";

    public AllowListPredicateFactory(Class<Void> configClass) {
        super(configClass);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Void v) {
        return (GatewayPredicate) serverWebExchange -> {
            String path = serverWebExchange.getRequest().getPath().toString();
            if (!path.matches(REGEX)) {
                return false;
            }
            String[] split = path.split("/");
            Integer integer = Integer.valueOf(split[2]);
            return (integer % 2 == 0);
        };
    }
}
