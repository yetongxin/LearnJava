package com.yetx.enums;

public enum  ProductStatusEnums implements CodeEnum{
    UP(1,"在架"),
    DOWN(0,"下架");


    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ProductStatusEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
