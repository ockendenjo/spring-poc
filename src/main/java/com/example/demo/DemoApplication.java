package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AllowListPredicateFactory af) {


        return builder.routes()
            .route("path_route", r -> r.path("/bing")
                .uri("https://www.bing.com/"))
            .route("path2", r -> r.path("/google")
                .uri("https://google.com"))
            .route("path_predicate", r -> r.predicate(af.apply(new AllowListPredicateFactory.Config(true))).uri("http://google.com"))
            .build();
    }

    @Bean
    public AllowListPredicateFactory allowListPredicateFactory() {
        return new AllowListPredicateFactory(AllowListPredicateFactory.Config.class);
    }
}
