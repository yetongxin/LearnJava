package com.yetx.service.impl;

import com.yetx.dto.OrderDTO;
import com.yetx.enums.SellResultEnums;
import com.yetx.exception.SellException;
import com.yetx.service.BuyerService;
import com.yetx.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    private Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);
    @Override
    public OrderDTO cancel(String openid, String orderId) {
        OrderDTO orderDTO = checkIfSelf(openid,orderId);
        if(orderDTO==null){
            logger.error("【删除订单】：根据Openid查询到的订单不存在");
            throw new SellException(SellResultEnums.ORDER_NOT_EXIST);
        }
        return orderService.cancelOrder(orderDTO);

    }

    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        return checkIfSelf(openid,orderId);
    }

    @Override
    public OrderDTO checkIfSelf(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOneByOrderId(orderId);
        if(orderDTO==null){
           return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            logger.error("【查询订单】：查询的openid与orderId对应的订单的openid不一致：openid:{},orderDTO的id：{}",openid,orderDTO.getBuyerOpenid());
            throw new SellException(SellResultEnums.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
