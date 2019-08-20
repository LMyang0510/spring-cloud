package com.example.springcloud.ribbon.consumerfeign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LMyang
 * @date 2019/8/20
 */
@RestController
public class FeignController {

    @Resource
    private FeignClientInterface feignClientInterface;

    @GetMapping("/feign")
    public String feign() {
        return "feign调用：" + feignClientInterface.hello();
    }
}
