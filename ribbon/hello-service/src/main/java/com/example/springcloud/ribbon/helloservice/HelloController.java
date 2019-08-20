package com.example.springcloud.ribbon.helloservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LMyang
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

    @GetMapping("/hello/list")
    public String hello(@RequestParam("list") List<String> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach(builder::append);
        return applicationName + ":" + port + "\t" + builder.toString();
    }

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str) {
        return applicationName + ":" + port + "\t" + str;
    }
}
