package com.example.springcloud.helloservice.service;

import java.util.List;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-24 14:40
 */
public interface HelloService {

    String hello();

    String hello(String str);

    List<String> hello(List<String> list);
}
