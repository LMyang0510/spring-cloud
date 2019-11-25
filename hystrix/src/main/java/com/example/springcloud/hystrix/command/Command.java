package com.example.springcloud.hystrix.command;

import com.example.springcloud.hystrix.dto.BaseDTO;

import java.util.List;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:03
 */
@FunctionalInterface
public interface Command {

    <D extends BaseDTO> Object execute(List<D> list);
}
