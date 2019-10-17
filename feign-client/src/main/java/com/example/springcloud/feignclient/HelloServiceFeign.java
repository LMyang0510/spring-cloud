package com.example.springcloud.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author LMyang
 */
@FeignClient("hello-service")
public interface HelloServiceFeign {

    @GetMapping("/hello")
    String hello();

    @GetMapping("/hello/{str}")
    String hello(@PathVariable("str") String str);

    @PostMapping("hello/list")
    List<String> hello(@RequestBody List<String> list);

}
