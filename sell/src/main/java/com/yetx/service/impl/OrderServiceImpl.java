package com.yetx.service.impl;

import com.yetx.convertor.OrderMaster2OrderDTOConvertor;
import com.yetx.daoobject.OrderDetail;
import com.yetx.daoobject.OrderMaster;
import com.yetx.daoobject.ProductInfo;
import com.yetx.dto.CartDTO;
import com.yetx.dto.OrderDTO;
import com.yetx.enums.OrderStatusEnums;
import com.yetx.enums.PayStatusEnums;
import com.yetx.enums.SellResultEnums;
import com.yetx.exception.SellException;
import com.yetx.repository.OrderDetailRepository;
import com.yetx.repository.OrderMasterRepository;
import com.yetx.service.OrderService;
import com.yetx.service.ProductInfoService;
import com.yetx.service.WebSocket;
import com.yetx.utils.RandomIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDetailRepository orderDetailRepo;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderMasterRepository orderMasterRepo;
    @Autowired
    private WebSocket webSocket;
    @Override
    @Transactional //一连串操作
    public OrderDTO createOrder(OrderDTO orderDTO){
        //将数据存到数据库
        String orderId = RandomIdUtils.getRandomId();
        BigDecimal sum = new BigDecimal(0);
        //1.查出所有productInfo
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        //2.计算总价,orderDTO里面很多属性是null，需要通过数据库获取
        for(OrderDetail orderDetail:orderDetailList){
            //根据orderDetail里面的productId到数据库里取到更准确的信息
            ProductInfo product = productInfoService.findById(orderDetail.getProductId());
            if(product==null){
                throw new SellException(SellResultEnums.PRODUCT_NOT_EXIST);
            }
            logger.info("根据productId查到的productInfo:{}",product.toString());

            logger.info("before sum为:{}",sum.toString());
            sum = sum.add(product.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())));
            logger.info("sum为:{}",sum.toString());
            //将orderDetail入库
            String detailId = RandomIdUtils.getRandomId();
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(detailId);

            BeanUtils.copyProperties(product,orderDetail);//将product的信息copy到orderDetail;
            logger.info("入库前的orderDetail:{}",orderDetail.toString());
            orderDetailRepo.save(orderDetail);

        }
        orderDTO.setOrderAmount(sum);

        //3.将orderMaster入库

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepo.save(orderMaster);
        //4.扣productInfo对应的库存

        List<CartDTO> cart = new ArrayList<>();
        for(OrderDetail orderDetail:orderDetailList){
            cart.add(new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity()));
        }
        productInfoService.decreaseStock(cart);

        //4.websocket通知
        webSocket.sendMessage("收到新订单");

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        OrderMaster orderMaster =  orderMasterRepo.findById(orderDTO.getOrderId()).orElse(null);
        if(orderMaster==null){
            throw new SellException(SellResultEnums.ORDER_NOT_EXIST);
        }
        logger.info("查询到的订单为orderMaster:{}",orderMaster.toString());
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnums.NEW_ORDER.getCode())){
            throw new SellException(SellResultEnums.ORDER_STATUS_ERROR);
        }
        orderMaster.setOrderStatus(OrderStatusEnums.CANCEL_ORDER.getCode());

        orderMasterRepo.save(orderMaster);
        logger.info("存入数据库之前的orderMaster:{}",orderMaster.toString());
        return OrderMaster2OrderDTOConvertor.OrderMaster2OrderDTO(orderMaster);
    }
    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterRepo.findById(orderDTO.getOrderId()).orElse(null);
        if(orderMaster==null){
            throw new SellException(SellResultEnums.ORDER_NOT_EXIST);
        }
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnums.NEW_ORDER.getCode())){
            throw new SellException(SellResultEnums.ORDER_STATUS_ERROR);
        }
        orderMaster.setOrderStatus(OrderStatusEnums.DONE_ORDER.getCode());
        return OrderMaster2OrderDTOConvertor.OrderMaster2OrderDTO(orderMaster);
    }
    @Override
    public Page<OrderDTO> findAllOrder(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepo.findAll(pageable);
        List<OrderMaster> orderMasterList = orderMasterPage.getContent();

        List<OrderDTO> orderDTOList = orderMasterList.stream()
                .map(OrderMaster2OrderDTOConvertor::OrderMaster2OrderDTO)
                .collect(Collectors.toList());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    public Page<OrderDTO> findBuyerAllOrder(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepo.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderMaster> orderMasterList = orderMasterPage.getContent();

        List<OrderDTO> orderDTOList = orderMasterList.stream()
                .map(OrderMaster2OrderDTOConvertor::OrderMaster2OrderDTO)
                .collect(Collectors.toList());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    public OrderDTO findOneByOrderId(String OrderId) {
        OrderMaster orderMaster = orderMasterRepo.findById(OrderId).orElse(null);
        if(orderMaster==null){
            throw new SellException(SellResultEnums.ORDER_NOT_EXIST);
        }

        return OrderMaster2OrderDTOConvertor.OrderMaster2OrderDTO(orderMaster);
    }

    @Override
    @Transactional
    public OrderDTO paidOrder(OrderDTO orderDTO) {
//        OrderDTO orderDTO1 = findOneByOrderId(orderDTO.getOrderId());
        OrderMaster orderMaster = orderMasterRepo.findById(orderDTO.getOrderId()).orElse(null);
        if(orderMaster==null){
            throw new SellException(SellResultEnums.ORDER_NOT_EXIST);
        }
        if(orderMaster.getPayStatus().equals(PayStatusEnums.PAID.getCode())){
            throw new SellException(SellResultEnums.PAID_STATUS_ERROR);
        }
        orderMaster.setPayStatus(PayStatusEnums.PAID.getCode());
        orderMasterRepo.save(orderMaster);

        //返回经过转换的orderDTO
        return OrderMaster2OrderDTOConvertor.OrderMaster2OrderDTO(orderMaster);

    }

}
