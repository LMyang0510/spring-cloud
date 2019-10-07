package com.example.springcloud.hystrix;

import com.example.springcloud.feignclient.HelloServiceFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LMyang
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @Resource
    private HelloServiceFeign helloServiceFeign;

    @GetMapping("/hello")
    public String hello() {
        String response = "feign调用：" + helloServiceFeign.hello();
        System.out.println(response);
        return response;
    }
}
