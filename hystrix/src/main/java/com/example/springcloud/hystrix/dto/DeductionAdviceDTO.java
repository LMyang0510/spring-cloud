package com.example.springcloud.hystrix.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeductionAdviceDTO extends BaseDTO {

    private String deductionOrderCode;
}
