package com.example.springcloud.hystrix;

import com.example.springcloud.hystrix.command.CommandTypeEnum;
import com.example.springcloud.hystrix.commander.DeductionAdviceCommander;
import com.example.springcloud.hystrix.dto.DeductionAdviceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 22:56
 */
public class CommandTest {

    public static void main(String[] args) {

        List<DeductionAdviceDTO> dtoList = new ArrayList<>();
        DeductionAdviceDTO dto = new DeductionAdviceDTO();
        dto.setStatus(0);
        dtoList.add(dto);
        dto = new DeductionAdviceDTO();
        dto.setStatus(1);
        dto.setDeductionOrderCode("KKJY1");
        dtoList.add(dto);

        System.out.println(new DeductionAdviceCommander().execute(CommandTypeEnum.AUDIT.name(), dtoList));
    }
}
