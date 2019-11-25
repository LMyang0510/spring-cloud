package com.example.springcloud.hystrix.execute;

import com.example.springcloud.hystrix.dto.BaseDTO;

import java.util.List;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:27
 */
@FunctionalInterface
public interface Execute {

    <D extends BaseDTO> Object execute(List<D> list);
}
