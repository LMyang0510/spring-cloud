package com.example.springcloud.ribbon.ribbonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author LMyang
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerRestTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRestTemplateApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}