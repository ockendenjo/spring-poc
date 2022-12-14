package com.example.demo;

import com.example.demo.filter.MyFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.function.Predicate;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AllowListPredicateFactory af, MyFilterFactory mf) {


        return builder.routes()
            .route("path_route", r -> r.path("/bing")
                .uri("https://www.bing.com/"))
            .route("path2", r -> r.path("/google")
                .uri("https://google.com"))
            .route("foo", r -> r.path("/foo").filters(f -> f.filter(mf.apply((Void) null))).uri("https://google.com"))
            .route("path_predicate", r -> r.predicate(af.apply((Void) null)).uri("http://google.com"))
            .route("path_predication2", r -> r.predicate(Predicate.not(af.apply((Void) null))).uri("https://www.bing.com"))
            .build();
    }

    @Bean
    public AllowListPredicateFactory allowListPredicateFactory() {
        return new AllowListPredicateFactory(null);
    }
}
