package com.yetx.enums;

public enum ListResultEnum {
    SUCCESS(0,"成功"),
    FAIL(1,"失败");

    private Integer code;
    private String msg;
    ListResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
