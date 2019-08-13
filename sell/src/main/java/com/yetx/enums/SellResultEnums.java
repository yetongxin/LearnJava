package com.yetx.enums;

public enum SellResultEnums {
    PRODUCT_UPLOAD_SUCCESS(10000,"商品上架成功"),
    PRODUCT_DOWNLOAD_SUCCESS(10001,"商品下架成功"),
    PRODUCT_CHANGE_SUCCESS(10002,"商品修改成功"),

    PRODUCT_NOT_EXIST(100,"该商品不存在"),
    PRODUCT_STOCK_ERROR(101,"商品库存错误"),
    ORDER_NOT_EXIST(102,"订单不存在"),
    ORDER_STATUS_ERROR(103,"订单状态错误"),
    PAID_STATUS_ERROR(104,"支付状态错误"),
    ORDER_PARM_ERROR(105,"订单参数错误"),
    ORDER_CREATE_SUCCESS(0,"成功"),
    ORDER_CREATE_FAIL(1,"失败"),
    ORDER_OWNER_ERROR(106,"订单所有者错误"),
    ORDER_CANCEL_OPENID_ORDERID_NOT_EQUALS_ERROR(107,"openid，orderid信息不一致"),
    WECHAT_MP_ERROR(1001,"微信网页授权错误"),
    PRODUCT_UPLOAD_STATUS_ERROR(107,"商品状态错误，无法上架"),
    PRODUCT_DOWNLOAD_STATUS_ERROR(108,"商品状态错误，无法下架"),

    SELLER_NOT_EXIST(2001,"用户不存在"),
    SELLER_PASSWORD_ERROR(2002,"密码错误"),
    SELLER_LOGOUT_SUCCESS(2010,"登出成功")
    ;

    private Integer code;
    private String msg;

    SellResultEnums(Integer code, String msg) {
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

