package com.yetx.service.impl;

import com.yetx.dto.OrderDTO;
import com.yetx.enums.OrderStatusEnums;
import com.yetx.service.BuyerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BuyerServiceImplTest {

    @Autowired
    private BuyerService buyerService;

    private final Logger logger = LoggerFactory.getLogger(BuyerServiceImplTest.class);

    private final String OPENID = "ew3euwhd7sjw9diwkq";
    private final String ORDERID = "1543591429765141970";
    @Test
    @Transactional
    public void cancel() {
        OrderDTO orderDTO = buyerService.cancel(OPENID,ORDERID);
        logger.info("【取消测试】:orderDTO:{}",orderDTO.toString());
        Assert.assertEquals(OrderStatusEnums.CANCEL_ORDER.getCode(),orderDTO.getOrderStatus());
    }

    @Test
    @Transactional
    public void findOneOrder() {
        OrderDTO orderDTO = buyerService.findOneOrder(OPENID,ORDERID);
        logger.info("【查询测试】:orderDTO:{}",orderDTO.toString());
        Assert.assertEquals("18868822111",orderDTO.getBuyerPhone());
    }


}