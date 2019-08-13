package com.yetx.service.impl;

import com.yetx.daoobject.OrderDetail;
import com.yetx.dto.OrderDTO;
import com.yetx.enums.PayStatusEnums;
import com.yetx.service.OrderService;
import com.yetx.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImplTest.class);
    private final String orderId = "21234812232";
    private final String detailId = "21lk321j434";
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductInfoService productInfoService;
    @Test
    @Transactional
    public void createOrder(){
        OrderDTO orderDTO = new OrderDTO();
        //orderDTO.setOrderId(orderId);
        orderDTO.setBuyerAddress("szu szu");//前台拿
        orderDTO.setBuyerName("biu biu");
        orderDTO.setBuyerOpenid("szu openid");
        orderDTO.setBuyerPhone("1588888888");

        List<OrderDetail> detailList = new ArrayList<>();
        OrderDetail detail1 = new OrderDetail();
        //detail1.setDetailId(detailId);
        detail1.setOrderId(orderId);
        detail1.setProductIcon("http://12345.com");//后台拿，前台只需提供productId和productQuantity即可
        detail1.setProductId("123456");
        detail1.setProductName("酸菜鱼");
        detail1.setProductQuantity(1);
        detail1.setProductPrice(new BigDecimal(20.2));
        detailList.add(detail1);

        orderDTO.setOrderDetailList(detailList);

        OrderDTO result = orderService.createOrder(orderDTO);

        Assert.assertEquals(new Integer(0),productInfoService.findById("123456").getProductStock());
        logger.info(result.toString());
    }
    @Test
    @Transactional
    public void cancelOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1543489650380492692");
        OrderDTO orderDTORes = orderService.cancelOrder(orderDTO);
        Assert.assertEquals(new Integer(2),orderDTORes.getOrderStatus());
        logger.info("取消订单后orderDto:{}",orderDTORes.toString());
    }
    @Test
    @Transactional
    public void finishOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1543490093102880618");
        OrderDTO orderDTORList = orderService.finishOrder(orderDTO);
        Assert.assertEquals(java.util.Optional.of(1).get(),orderDTORList.getOrderStatus());
        logger.info("完成订单后orderDto:{}",orderDTORList.toString());
    }
    @Test
    public void findAllOrder(){
        PageRequest pageable = PageRequest.of(0,3);
        Page<OrderDTO> page = orderService.findAllOrder(pageable);
        Assert.assertEquals(4,page.getTotalElements());
        page.getContent().stream().forEach( order->logger.info(order.toString()));
    }
    @Test
    public void findBuyerAllOrder(){
        PageRequest pageable = PageRequest.of(0,3);
        Page<OrderDTO> page = orderService.findBuyerAllOrder("szu openid",pageable);
        Assert.assertEquals(4,page.getTotalElements());
        page.getContent().stream().forEach(order->logger.info(order.toString()));
    }
    @Test
    public void findOneByOrderId(){
        String orderId = "1543489650380492692";
        OrderDTO orderDTO = orderService.findOneByOrderId(orderId);
        Assert.assertEquals("biu biu",orderDTO.getBuyerName());
    }
    @Test
    @Transactional
    public void paidOrder(){
        String orderId = "1543489650380492692";
        OrderDTO orderDTO = orderService.findOneByOrderId(orderId);
        OrderDTO orderDTOres = orderService.paidOrder(orderDTO);
        Assert.assertEquals(PayStatusEnums.PAID.getCode(),orderDTOres.getPayStatus());
    }
}