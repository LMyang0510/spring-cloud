package com.example.springcloud.hystrix.execute;

import lombok.Getter;

/**
 * 描述信息：
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:39
 */
public enum ExecuteTypeEnum {

    KKJY("扣款建议"),
    KKD("扣款单"),
    JSD("结算单"),
    ;

    @Getter
    String desc;

    ExecuteTypeEnum(String desc) {
        this.desc = desc;
    }

    public static ExecuteTypeEnum getExecuteTypeEnum(String name) {
        for (ExecuteTypeEnum value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
