package com.example.springcloud.hystrix;

import com.example.springcloud.hystrix.execute.DeductionAdviceExecute;
import com.example.springcloud.hystrix.execute.Execute;
import com.example.springcloud.hystrix.execute.ExecuteTypeEnum;

import java.util.EnumMap;
import java.util.Map;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:24
 */
public class CommandExecuteFactory {

    private static Map<ExecuteTypeEnum, Execute> map = new EnumMap<>(ExecuteTypeEnum.class);

    private CommandExecuteFactory() {

    }

    static {
        map.put(ExecuteTypeEnum.KKJY, new DeductionAdviceExecute());
    }

    public static Execute createExecute(ExecuteTypeEnum executeTypeEnum) {
        return map.get(executeTypeEnum);
    }
}
