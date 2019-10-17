package com.example.springcloud.helloservice.controller;

import com.example.springcloud.helloservice.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return applicationName + ":" + port + helloService.hello();
    }

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str) {
        return applicationName + ":" + port + helloService.hello(str);
    }

    @PostMapping("/hello/list")
    public List<String> hello(@RequestBody List<String> list) {
        List<String> stringList = helloService.hello(list);
        List<String> respList = new ArrayList<>(stringList.size());
        stringList.forEach(string -> respList.add(applicationName + ":" + port + string));
        return respList;
    }
}
