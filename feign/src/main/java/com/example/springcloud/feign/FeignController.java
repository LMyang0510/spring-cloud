package com.example.springcloud.feign;

import com.example.springcloud.feignclient.HelloServiceApiFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Feign方式调用服务
 *
 * @author LMyang
 */
@Slf4j
@RestController
public class FeignController {

    @Resource
    private HelloServiceApiFeign helloServiceFeign;

    @GetMapping("/hello")
    public String hello() {
        return "feign调用：" + helloServiceFeign.hello();
    }

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable("str") String str) {
        return "feign调用：" + helloServiceFeign.hello(str);
    }

    @PostMapping("hello/list")
    public List<String> hello(@RequestBody List<String> list) {
        List<String> resultList = new ArrayList<>(list.size());
        list = helloServiceFeign.hello(list);
        list.forEach(string -> resultList.add("feign调用：" + string));
        log.info(resultList.toString());
        return resultList;
    }
}
