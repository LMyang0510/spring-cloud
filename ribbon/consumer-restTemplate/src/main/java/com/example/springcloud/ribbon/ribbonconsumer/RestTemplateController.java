package com.example.springcloud.ribbon.ribbonconsumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author LMyang
 */
@RestController
public class RestTemplateController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/restTemplate")
    public String restTemplate() {
        String response = "restTemplate调用：" +
                restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
        System.out.println(response);
        return response;
    }

    @GetMapping("/restTemplate/{str}")
    public String restTemplate(@PathVariable("str") String str) {
        String response = "restTemplate调用：" +
                restTemplate.getForEntity("http://HELLO-SERVICE/hello/" + str, String.class).getBody();
        System.out.println(response);
        return response;
    }

}
