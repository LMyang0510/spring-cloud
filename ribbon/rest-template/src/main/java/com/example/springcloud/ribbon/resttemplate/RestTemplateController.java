package com.example.springcloud.ribbon.resttemplate;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * RestTemplate方式调用服务
 *
 * @author LMyang
 */
@RestController
public class RestTemplateController {

    private static final String SERVER_URL = "http://HELLO-SERVICE/hello";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "restTemplate调用：" + restTemplate.getForEntity(SERVER_URL, String.class).getBody();
    }

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str) {
        return "restTemplate调用：" + restTemplate.getForEntity(SERVER_URL + str, String.class).getBody();
    }

    @GetMapping("/hello/list")
    @SuppressWarnings("unchecked")
    public List hello(@RequestParam("list") List<String> list) {
        List<String> stringList = restTemplate.getForEntity(SERVER_URL + "/list", List.class, list).getBody();
        if (CollectionUtils.isEmpty(stringList)) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>(stringList.size());
        stringList.forEach(string -> result.add("restTemplate调用：" + string));
        return result;
    }
}
