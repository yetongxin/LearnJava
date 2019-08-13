package com.yetx.service;

import com.yetx.dto.OrderDTO;

public interface BuyerService {
    //调用orderService,对获取到的信息进行检查;

    /** 取消订单*/
    OrderDTO cancel(String openid, String orderId);

    /** 通过openid和orderid寻找某个订单*/
    public OrderDTO findOneOrder(String openid,String orderId);

    /** 判断该订单是否属于该openid,有两个参数时需要判断两个参数是否对应同一个订单，不属于则抛出异常*/
    public OrderDTO checkIfSelf(String openid, String orderDTO);

}
