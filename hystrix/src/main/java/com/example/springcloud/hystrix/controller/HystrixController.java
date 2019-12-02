package com.example.springcloud.hystrix.controller;

import com.example.springcloud.hystrix.service.HystrixService;
import com.example.springcloud.serviceapi.HelloServiceApi;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author LMyang
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController implements HelloServiceApi {

    @Resource
    private HystrixService hystrixService;

    @PostConstruct
    public void init() {
        HystrixRequestContext.initializeContext();
    }

    @GetMapping("/hello")
    public String hello() {
        return hystrixService.hello();
    }

    @GetMapping("/{str}")
    public String hello(@PathVariable("str") String str) {
        return hystrixService.hello(str);
    }

    @PostMapping("/list")
    public List<String> hello(@RequestBody List<String> list) {
        return hystrixService.hello(list);
    }
}
