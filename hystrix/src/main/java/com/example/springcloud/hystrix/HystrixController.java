package com.example.springcloud.hystrix;

import com.example.springcloud.feignclient.HelloServiceFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    private HelloServiceFeign helloServiceFeign;

    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod = "fallBack")
    public String hello() {
        return "hystrix-feign调用：" + helloServiceFeign.hello();
    }

    @GetMapping("/hello/{str}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public String hello(@PathVariable("str") String str) {
        return "hystrix-feign调用：" + helloServiceFeign.hello(str);
    }

    @GetMapping("/hello/list")
    @HystrixCommand(fallbackMethod = "fallBack")
    public String hello(@RequestParam("list") List<String> list) {
        return "hystrix-feign调用：" + helloServiceFeign.hello(list);
    }

    public String fallBack() {
        return "hello() 接口调用异常，服务降级处理";
    }

    public String fallBack(String str) {
        return "hello(String str) 接口调用异常，服务降级处理";
    }

    public String fallBack(List<String> list) {
        return "hello(List<String> list) 接口调用异常，服务降级处理";
    }
}
