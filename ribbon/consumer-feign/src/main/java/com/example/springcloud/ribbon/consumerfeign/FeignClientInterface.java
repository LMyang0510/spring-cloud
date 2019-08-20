package com.example.springcloud.ribbon.consumerfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LMyang
 * @date 2019/8/20
 */
@FeignClient("hello-service")
public interface FeignClientInterface {

    @GetMapping("/hello")
    String hello();
}
