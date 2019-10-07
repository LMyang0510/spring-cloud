package com.example.springcloud.ribbon.resttemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author LMyang
 */
@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        String response = "restTemplate调用：" +
                restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
        System.out.println(response);
        return response;
    }

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str) {
        String response = "restTemplate调用：" +
                restTemplate.getForEntity("http://HELLO-SERVICE/hello/" + str, String.class).getBody();
        System.out.println(response);
        return response;
    }

}
