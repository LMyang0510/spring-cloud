package com.example.springcloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Hello Service
 *
 * @author LMyang
 * @date 2019/10/11
 */
@FeignClient(value = "hello-service", fallback = HelloServiceApiFallback.class)
public interface HelloServiceApi {

    @GetMapping("/hello/hello")
    String hello();

    @GetMapping("/hello/{str}")
    String hello(@PathVariable("str") String str);

    @PostMapping("/hello/list")
    List<String> hello(@RequestBody List<String> list);
}
