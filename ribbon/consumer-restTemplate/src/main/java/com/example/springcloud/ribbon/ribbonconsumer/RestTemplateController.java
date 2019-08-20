package com.example.springcloud.ribbon.ribbonconsumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author LMyang
 * @date 2019/8/6
 */
@RestController
public class RestTemplateController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/restTemplate")
    public String restTemplate() {
        return "restTemplate调用：" + restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }
}
