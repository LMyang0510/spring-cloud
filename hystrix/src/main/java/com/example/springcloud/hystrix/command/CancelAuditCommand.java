package com.example.springcloud.hystrix.command;

import com.example.springcloud.hystrix.dto.BaseDTO;
import com.example.springcloud.hystrix.execute.ExecuteTypeEnum;

import java.util.List;


/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:54
 */
public class CancelAuditCommand extends BaseCommand {

    {
        exceptionMsg = "不是已审核的数据！！！";
    }

    public CancelAuditCommand(ExecuteTypeEnum executeTypeEnum) {
        super(executeTypeEnum);
    }

    @Override
    public <D extends BaseDTO> Object execute(List<D> list) {
        return super.execute(list, CommandTypeEnum.AUDIT.value);
    }
}
