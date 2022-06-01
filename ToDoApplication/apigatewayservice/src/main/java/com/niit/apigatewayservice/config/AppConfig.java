package com.niit.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {


    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p
                        .path("/myapp/auth/**") //http://localhost:3333/myapp/auth/**
                        .uri("http://localhost:8080/")) //http://localhost:5656/myapp/auth/**
                .route(p->p
                        .path("/myapp/v1/**") //http://localhost:3333/myapp/v1/task/**
                        .uri("http://localhost:9696/")) //http://localhost:9797/myapp/v1/task/**
                .build();
    }
}
