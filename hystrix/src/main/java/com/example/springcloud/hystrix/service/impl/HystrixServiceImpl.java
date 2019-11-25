package com.example.springcloud.hystrix.service.impl;

import com.example.springcloud.feignclient.HelloServiceApiFeign;
import com.example.springcloud.hystrix.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 功能描述
 *
 * @author LMyang
 * @date 2019/10/17
 */
@Slf4j
@Service
public class HystrixServiceImpl implements HystrixService {

    @Resource
    private HelloServiceApiFeign helloServiceFeign;

    @Override
    // 请求熔断 + 服务降级
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String hello() {
        return "hystrix-feign调用：" + helloServiceFeign.hello();
    }


    /**
     * .@CacheResult @CacheKey @CacheRemove 属于 JSR 107 缓存规范
     * .@CacheResult 中 指定 cacheKeyMethod 后 @CacheKey 不生效
     * <p>
     * 请求熔断 和 请求合并 注解不能同时出现在一个方法上，否则会报异常 java.lang.IllegalStateException:
     * method cannot be annotated with HystrixCommand and HystrixCollapser annotations at the same time;
     * <p>
     * 请求合并指定的 batchMethod 方法必须有 @HystrixCommand 注解信息，否则会报异常 java.lang.IllegalStateException:
     * batch method must be annotated with HystrixCommand annotation
     * <p>
     */
    @Override
    // 请求缓存
    @CacheResult(cacheKeyMethod = "cacheKeyMethod")
    // 请求合并
    @HystrixCollapser(batchMethod = "hello",
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
    public String hello(String str) {
        return "hystrix-feign调用：" + helloServiceFeign.hello(str);
    }


    @Override
    // 请求熔断 + 服务降级
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public List<String> hello(List<String> list) {
        List<String> resultList = new ArrayList<>(list.size());
        list = helloServiceFeign.hello(list);
        list.forEach(s -> resultList.add("hystrix-feign调用：" + s));
        log.info(resultList.toString());
        return resultList;
    }


    /*
     * fallbackMethod
     */
    public String fallbackMethod() {
        return "hello() 接口调用异常，服务降级处理";
    }

    public List<String> fallbackMethod(List<String> list) {
        return Collections.singletonList("hello(List<String> list) 接口调用异常，服务降级处理");
    }


    /* 请求缓存 */
    public String cacheKeyMethod(String str) {
        return str + "xxx";
    }
}
