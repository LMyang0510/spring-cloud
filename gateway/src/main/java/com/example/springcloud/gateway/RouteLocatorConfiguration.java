package com.example.springcloud.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由映射配置
 *
 * @author LMyang
 * @date 2019/12/3
 */
@Configuration
public class RouteLocatorConfiguration {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/hello/**").uri("http://localhost:9001"))
                .route(r -> r.path("/hystrix/**").uri("http://localhost:8003"))
                .route(r -> r.path("/feign/**").uri("http://localhost:8004"))
                .build();
    }
}
