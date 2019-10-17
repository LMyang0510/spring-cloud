package com.example.springcloud.hystrix.service;

import java.util.List;

/**
 * 功能描述
 *
 * @author LMyang
 * @date 2019/10/17
 */
public interface HystrixService {

    String hello();

    String hello(String str);

    List<String> hello(List<String> list);
}
