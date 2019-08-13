package com.yetx.controller;

import com.yetx.dto.OrderDTO;
import com.yetx.exception.SellException;
import com.yetx.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    private final Logger logger = LoggerFactory.getLogger(SellerOrderController.class);
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView orderlist(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                  @RequestParam(value = "size",defaultValue = "5") Integer size,
                                  Map<String,Object> map){
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findAllOrder(pageRequest);
        map.put("orderDTOPage",orderDTOPage);
        map.put("page",page);
        map.put("size",size);
        System.out.println("page:{}"+page+"  "+map.get("page"));
        return new ModelAndView("order/list",map);
    }
    @GetMapping("/detail")
    public ModelAndView orderdetail(@RequestParam(value = "orderId") String orderId,
                                    Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOneByOrderId(orderId);
            //orderService.cancelOrder(orderDTO);

        }
        catch (SellException e){
            logger.info("订单详情出错:{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error","exceptionMsg",map);
        }
        map.put("order",orderDTO);
        return new ModelAndView("order/detail",map);
    }
    @GetMapping("cancel")
    public ModelAndView cancelOrder(@RequestParam(value = "orderId") String orderId,
                                    Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOneByOrderId(orderId);
            orderService.cancelOrder(orderDTO);

        }
        catch (SellException e){
            logger.info("订单取消出错:{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //map.put("order",orderDTO);
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
