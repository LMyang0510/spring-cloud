package com.example.springcloud.ribbon.consumerfeign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LMyang
 */
@RestController
public class FeignController {

    @Resource
    private FeignClientInterface feignClientInterface;

    @GetMapping("/feign")
    public String feign() {
        String response = "feign调用：" + feignClientInterface.hello();
        System.out.println(response);
        return response;
    }

    @GetMapping("/feign/list")
    public String feign(@RequestParam("list") List<String> list) {
        String response = "feign调用：" + feignClientInterface.hello(list);
        System.out.println(response);
        return response;
    }

    @GetMapping("/feign/{str}")
    public String feign(@PathVariable("str") String str) {
        String response = "feign调用：" + feignClientInterface.hello(str);
        System.out.println(response);
        return response;
    }
}
