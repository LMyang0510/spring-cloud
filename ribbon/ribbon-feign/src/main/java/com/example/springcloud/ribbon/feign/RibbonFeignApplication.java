package com.example.springcloud.ribbon.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LMyang
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.springcloud")
@SpringBootApplication(scanBasePackages = "com.example.springcloud")
public class RibbonFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonFeignApplication.class, args);
    }

}
