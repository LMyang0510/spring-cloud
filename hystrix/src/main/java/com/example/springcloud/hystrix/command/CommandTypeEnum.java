package com.example.springcloud.hystrix.command;

/**
 * 描述信息：命令枚举
 *
 * @author LMyang
 * @version 1.0
 * @date 2019-11-25 21:04
 */
public enum CommandTypeEnum {

    AUDIT(0, "审核"),
    CANCEL_AUDIT(1, "取消审核"),
    DELETE(2, "删除"),
    CLEAR(3, "清除"),
    ;

    public int value;
    public String desc;

    CommandTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static CommandTypeEnum getCommandTypeEnum(String comandType) {
        for (CommandTypeEnum value : values()) {
            if (value.name().equals(comandType)) {
                return value;
            }
        }
        return null;
    }
}
