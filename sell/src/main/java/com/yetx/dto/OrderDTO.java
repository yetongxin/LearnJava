package com.yetx.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yetx.convertor.DateSerialize;
import com.yetx.daoobject.OrderDetail;
import com.yetx.enums.OrderStatusEnums;
import com.yetx.enums.PayStatusEnums;
import com.yetx.utils.EnumUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class OrderDTO {
    private String orderId;

    private String buyerOpenid;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnums.NEW_ORDER.getCode();

    private Integer payStatus = PayStatusEnums.UNPAID.getCode();

    @JsonSerialize(using = DateSerialize.class)
    private Date createTime;
    
    @JsonSerialize(using = DateSerialize.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerOpenid() {
        return buyerOpenid;
    }

    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", buyerOpenid='" + buyerOpenid + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerPhone='" + buyerPhone + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderStatus=" + orderStatus +
                ", payStatus=" + payStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
    @JsonIgnore
    public OrderStatusEnums getOrderStatusEnums(Integer code){
        return EnumUtils.getByCode(code,OrderStatusEnums.class);
    }
    @JsonIgnore
    public PayStatusEnums getPayStatusEnums(Integer code){
        return EnumUtils.getByCode(code,PayStatusEnums.class);
    }
}

