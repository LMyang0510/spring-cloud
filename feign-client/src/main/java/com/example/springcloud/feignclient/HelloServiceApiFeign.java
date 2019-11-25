package com.example.springcloud.feignclient;

import com.example.springcloud.serviceapi.HelloServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author LMyang
 */
@FeignClient("hello-service")
public interface HelloServiceApiFeign extends HelloServiceApi {

}
