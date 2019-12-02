package com.example.springcloud.serviceapi;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

/**
 * 描述信息：HelloServiceApi服务降级逻辑处理
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-12-01 22:54
 */
@Component
public class HelloServiceApiFallback implements HelloServiceApi {

    @Override
    public String hello() {
        return "hello() fallback";
    }

    @Override
    public String hello(@PathVariable("str") String str) {
        return "hello(@PathVariable(\"str\") String str) fallback";
    }

    @Override
    public List<String> hello(@RequestBody List<String> list) {
        return Collections.singletonList("@RequestBody hello(List<String> list) fallback");
    }
}
