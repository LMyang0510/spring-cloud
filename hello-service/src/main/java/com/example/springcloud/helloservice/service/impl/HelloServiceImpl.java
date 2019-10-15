package com.example.springcloud.helloservice.service.impl;

import com.example.springcloud.helloservice.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        sleep();
        return "_" + System.currentTimeMillis();
    }

    @Override
    public String hello(String str) {
        sleep();
        return str + "_" + System.currentTimeMillis();
    }

    @Override
    public String hello(List<String> list) {
        sleep();
        return list + "_" + System.currentTimeMillis();
    }

    private void sleep() {
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            log.warn("线程被打断", e);
        }
    }
}
