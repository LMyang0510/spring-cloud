package com.example.springcloud.hystrix.controller;

import com.example.springcloud.hystrix.service.HystrixService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LMyang
 */
@RestController
public class HystrixController {

    @Resource
    private HystrixService hystrixService;

    @GetMapping("/hello")
    public String hello() {
        return hystrixService.hello();
    }

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str) {
        HystrixRequestContext.initializeContext();
        return hystrixService.hello(str);
    }

    @GetMapping("/hello/list")
    public List<String> hello(@RequestParam("list") List<String> list) {
        return hystrixService.hello(list);
    }
}
