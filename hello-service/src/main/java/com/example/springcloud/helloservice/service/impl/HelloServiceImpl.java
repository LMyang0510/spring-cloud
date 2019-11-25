package com.example.springcloud.helloservice.service.impl;

import com.example.springcloud.helloservice.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello Service 实现
 *
 * @author LMyang
 * @date 2019/10/11
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "_" + sleep();
    }

    @Override
    public String hello(String str) {
        return "_" + str + "_" + sleep();
    }

    @Override
    public List<String> hello(List<String> list) {
        int sleep = sleep();
        List<String> resultList = new ArrayList<>(list.size());
        list.forEach(string -> resultList.add("_" + string + "_" + sleep));
        return resultList;
    }

    private int sleep() {
        int nextInt = new Random().nextInt(1500);
        try {
            Thread.sleep(nextInt);
        } catch (InterruptedException e) {
            log.warn("线程被打断", e);
        }
        return nextInt;
    }
}
