package com.example.springcloud.helloservice.controller;

import com.example.springcloud.helloservice.service.HelloService;
import com.example.springcloud.serviceapi.HelloServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LMyang
 */
@Slf4j
@RestController
public class HelloController implements HelloServiceApi {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;

    @Resource
    private HelloService helloService;

    @Override
    public String hello() {
        String result = applicationName + ":" + port + helloService.hello();
        log.info(result);
        return result;
    }

    @Override
    public String hello(@PathVariable("str") String str) {
        String result = applicationName + ":" + port + helloService.hello(str);
        log.info(result);
        return result;
    }

    @Override
    public List<String> hello(@RequestBody List<String> list) {
        List<String> resultList = new ArrayList<>(list.size());
        list = helloService.hello(list);
        list.forEach(s -> resultList.add(applicationName + ":" + port + s));
        log.info(resultList.toString());
        return resultList;
    }
}
