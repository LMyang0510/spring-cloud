package com.example.springcloud.ribbon.consumerfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author LMyang
 */
@FeignClient("hello-service")
public interface FeignClientInterface {

    @GetMapping("/hello")
    String hello();

    @GetMapping("/hello/{str}")
    String hello(@PathVariable("str") String str);

    @GetMapping("hello/list")
    String hello(@RequestParam("list") List<String> list);

}
