package com.example.springcloud.hystrix.command;

import com.example.springcloud.hystrix.CommandExecuteFactory;
import com.example.springcloud.hystrix.dto.BaseDTO;
import com.example.springcloud.hystrix.execute.Execute;
import com.example.springcloud.hystrix.execute.ExecuteTypeEnum;

import java.util.List;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:55
 */
public class BaseCommand implements Command {

    String exceptionMsg;
    private Execute execute;

    public BaseCommand(ExecuteTypeEnum executeTypeEnum) {
        execute = CommandExecuteFactory.createExecute(executeTypeEnum);
    }

    @Override
    public <D extends BaseDTO> Object execute(List<D> list) {
        return execute.execute(list);
    }

    protected <D extends BaseDTO> Object execute(List<D> list, Integer notEqualsStatus) {
        if (list.stream().anyMatch(d -> notEqualsStatus.intValue() != d.getStatus())) {
            throw new RuntimeException(exceptionMsg);
        }
        return execute.execute(list);
    }
}
