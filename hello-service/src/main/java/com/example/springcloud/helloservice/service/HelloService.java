package com.example.springcloud.helloservice.service;

import java.util.List;

/**
 * Hello Service
 *
 * @author LMyang
 * @date 2019/10/11
 */
public interface HelloService {

    String hello();

    String hello(String str);

    String hello(List<String> list);
}
