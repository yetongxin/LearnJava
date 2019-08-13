package com.yetx.convertor;

import com.yetx.daoobject.OrderDetail;
import com.yetx.daoobject.OrderMaster;
import com.yetx.dto.OrderDTO;
import com.yetx.repository.OrderDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMaster2OrderDTOConvertor {

    private final static Logger logger = LoggerFactory.getLogger(OrderMaster2OrderDTOConvertor.class);
    //@Autowired
    private static OrderDetailRepository orderDetailRepo;

    @Autowired//静态注入
    public void setOrderDetailRepo(OrderDetailRepository orderDetailRepo2){
        OrderMaster2OrderDTOConvertor.orderDetailRepo = orderDetailRepo2;
    }


    public static OrderDTO OrderMaster2OrderDTO(OrderMaster orderMaster){
        //logger.info("进入转换器:{}",orderMaster.toString());
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetail> orderDetailList = orderDetailRepo.findByOrderId(orderMaster.getOrderId());
        orderDTO.setOrderDetailList(orderDetailList);
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> orderMasterList2OrderDTOList(List<OrderMaster> orderMasterList){
        return orderMasterList.stream()
                .map(orderMaster->OrderMaster2OrderDTO(orderMaster))
                .collect(Collectors.toList());

    }
}
