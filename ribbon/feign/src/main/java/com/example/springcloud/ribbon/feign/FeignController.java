package com.example.springcloud.ribbon.feign;

import com.example.springcloud.feignclient.HelloServiceFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Feign方式调用服务
 *
 * @author LMyang
 */
@RestController
public class FeignController {

    @Resource
    private HelloServiceFeign helloServiceFeign;

    @GetMapping("/hello")
    public String hello() {
        return "feign调用：" + helloServiceFeign.hello();
    }

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str) {
        return "feign调用：" + helloServiceFeign.hello(str);
    }

    @GetMapping("/hello/list")
    public String hello(@RequestParam("list") List<String> list) {
        return "feign调用：" + helloServiceFeign.hello(list);
    }
}
