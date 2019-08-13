package com.yetx.enums;

public enum PayStatusEnums implements CodeEnum{
    UNPAID(0,"未支付"),
    PAID(1,"已支付");

    private Integer code;
    private String msg;

    PayStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
