package com.example.springcloud.hystrix.execute;

import com.example.springcloud.hystrix.dto.BaseDTO;
import com.example.springcloud.hystrix.dto.DeductionAdviceDTO;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:16
 */
public class DeductionAdviceExecute implements Execute {

    public <D extends BaseDTO> boolean check(List<D> list) {
        List<DeductionAdviceDTO> dtoList = (List<DeductionAdviceDTO>) list;
        if (dtoList.stream().anyMatch(dto -> StringUtils.isNotBlank(dto.getDeductionOrderCode()))) {
            throw new RuntimeException("已生成下游单据");
        }
        return list.isEmpty();
    }

    @Override
    public <D extends BaseDTO> Object execute(List<D> list) {
        return list;
    }
}
