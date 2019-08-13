package com.yetx.enums;


public enum OrderStatusEnums implements CodeEnum{

    NEW_ORDER(0, "新订单"),
    DONE_ORDER(1,"已完成订单"),
    CANCEL_ORDER(2,"已撤销订单");


    private Integer code;
    private String msg;

    OrderStatusEnums(Integer code, String msg) {
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
