package com.example.springcloud.serviceapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Hello Service
 *
 * @author LMyang
 * @date 2019/10/11
 */
@RequestMapping("/helloService")
public interface HelloServiceApi {

    @GetMapping("/hello")
    String hello();

    @GetMapping("/hello/{str}")
    String hello(@PathVariable("str") String str);

    @PostMapping("hello/list")
    List<String> hello(@RequestBody List<String> list);
}
