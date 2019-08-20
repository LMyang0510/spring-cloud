package com.example.springcloud.ribbon.helloservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LMyang
 * @date 2019/8/6
 */
@RestController
public class HelloController {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String hello() {
        return applicationName + ":" + port;
    }
}
