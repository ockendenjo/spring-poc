package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

public class AllowListPredicateFactory extends AbstractRoutePredicateFactory<AllowListPredicateFactory.Config> {

    private static final String REGEX = "^/users/\\d+$";

    public AllowListPredicateFactory(Class<AllowListPredicateFactory.Config> configClass) {
        super(configClass);
    }

    @Override
    public Predicate<ServerWebExchange> apply(AllowListPredicateFactory.Config c) {
        return (GatewayPredicate) serverWebExchange -> {
            String path = serverWebExchange.getRequest().getPath().toString();
            if (!path.matches(REGEX)) {
                return false;
            }
            String[] split = path.split("/");
            Integer integer = Integer.valueOf(split[2]);
            return (integer % 2 == 0) && c.isInAllowList();
        };
    }

    @Getter
    @AllArgsConstructor
    public static class Config {
        private final boolean inAllowList;
    }
}
