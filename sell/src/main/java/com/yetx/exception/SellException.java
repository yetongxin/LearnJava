package com.yetx.exception;

import com.yetx.enums.SellResultEnums;

public class SellException extends RuntimeException{

    private Integer code;
    public SellException(String msg,Integer code){
        super(msg);
        this.code = code;
    }
    public SellException(SellResultEnums sellResultEnums){
        super(sellResultEnums.getMsg());
        this.code = sellResultEnums.getCode();
    }

}
