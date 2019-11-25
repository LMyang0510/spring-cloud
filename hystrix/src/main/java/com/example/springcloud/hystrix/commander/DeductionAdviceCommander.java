package com.example.springcloud.hystrix.commander;

import com.example.springcloud.hystrix.command.BaseCommand;
import com.example.springcloud.hystrix.command.CancelAuditCommand;
import com.example.springcloud.hystrix.command.CommandTypeEnum;
import com.example.springcloud.hystrix.dto.BaseDTO;
import com.example.springcloud.hystrix.dto.DeductionAdviceDTO;
import com.example.springcloud.hystrix.execute.ExecuteTypeEnum;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 22:05
 */
public class DeductionAdviceCommander {

    private Map<CommandTypeEnum, BaseCommand> commandMap = new EnumMap<>(CommandTypeEnum.class);

    {
        commandMap.put(CommandTypeEnum.AUDIT, new BaseCommand(ExecuteTypeEnum.KKJY));
        commandMap.put(CommandTypeEnum.CANCEL_AUDIT, new CancelAuditCommand(ExecuteTypeEnum.KKJY));

        // 清除操作
        commandMap.put(CommandTypeEnum.CLEAR, new BaseCommand(ExecuteTypeEnum.KKJY) {
            @Override
            public <D extends BaseDTO> Object execute(List<D> list) {
                list.clear();
                return true;
            }
        });
    }

    public Object execute(String commandType, List<DeductionAdviceDTO> list) {
        return commandMap.get(CommandTypeEnum.getCommandTypeEnum(commandType)).execute(list);
    }
}
