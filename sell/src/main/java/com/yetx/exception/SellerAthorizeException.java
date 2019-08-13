package com.yetx.exception;

public class SellerAthorizeException extends RuntimeException {
    private String msg;
    private Integer code;

    public SellerAthorizeException() {
    }

    public SellerAthorizeException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}
