package com.example.springcloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LMyang
 */
@EnableFeignClients(basePackages = "com.example.springcloud.serviceapi")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.example.springcloud")
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

}
