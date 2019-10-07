package com.example.springcloud.ribbon.feign;

import com.example.springcloud.feignclient.HelloServiceFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LMyang
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Resource
    private HelloServiceFeign helloServiceFeign;

    @GetMapping("/hello")
    public String hello() {
        String response = "feign调用：" + helloServiceFeign.hello();
        System.out.println(response);
        return response;
    }

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str) {
        String response = "feign调用：" + helloServiceFeign.hello(str);
        System.out.println(response);
        return response;
    }

    @GetMapping("/hello/list")
    public String hello(@RequestParam("list") List<String> list) {
        String response = "feign调用：" + helloServiceFeign.hello(list);
        System.out.println(response);
        return response;
    }
}
