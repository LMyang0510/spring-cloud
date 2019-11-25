package com.example.springcloud.hystrix.controller;

import com.example.springcloud.hystrix.service.HystrixService;
import com.example.springcloud.serviceapi.HelloServiceApi;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author LMyang
 */
@RestController
public class HystrixController implements HelloServiceApi {

    @Resource
    private HystrixService hystrixService;

    @PostConstruct
    public void init() {
        HystrixRequestContext.initializeContext();
    }

    @Override
    public String hello() {
        return hystrixService.hello();
    }

    @Override
    public String hello(@PathVariable("str") String str) {
        return hystrixService.hello(str);
    }

    @Override
    public List<String> hello(@RequestBody List<String> list) {
        return hystrixService.hello(list);
    }
}
