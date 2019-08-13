package com.yetx.controller;

import com.yetx.convertor.OrderForm2OrderDTOConvertor;
import com.yetx.dto.OrderDTO;
import com.yetx.enums.SellResultEnums;
import com.yetx.exception.SellException;
import com.yetx.formobject.OrderForm;
import com.yetx.service.BuyerService;
import com.yetx.service.OrderService;
import com.yetx.utils.ResultUtils;
import com.yetx.viewobject.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    private final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResultVO<Map<String,String>> createOrder(@RequestBody @Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            logger.error("【创建订单】：传入参数错误，传入的参数为{}",orderForm.toString());
            throw new SellException(bindingResult.getFieldError().getDefaultMessage(),
                    SellResultEnums.ORDER_PARM_ERROR.getCode());
        }
        if(orderForm.getItems().isEmpty()){
            logger.error("【创建订单】：购物车内不能为空，传入的参数为{}",orderForm.toString());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConvertor.OrderForm2OrderDTO(orderForm);

        OrderDTO orderDTOResult = orderService.createOrder(orderDTO);

        ResultVO<Map<String,String>> resultVO = new ResultVO<>();
        Map<String,String> SellResData = new HashMap<>();
        SellResData.put("orderId",orderDTOResult.getOrderId());



        return ResultUtils.success(SellResData);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultVO<List<OrderDTO>> listOrder(@RequestParam(value = "openid") String openid,
                                                 @RequestParam(value = "page",defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<OrderDTO> resPage = orderService.findBuyerAllOrder(openid,pageRequest);
        return ResultUtils.success(resPage.getContent());
    }

    //防止在controller中写太多的逻辑代码，将逻辑代码转移到另一个service中
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ResultVO<OrderDTO> listDetail(@RequestParam(value = "openid")String openid,
                                         @RequestParam(value = "orderId")String orderId){
        OrderDTO resOrder = buyerService.findOneOrder(openid,orderId);
        return ResultUtils.success(resOrder);

    }
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public ResultVO<OrderDTO> cancelOrder(@RequestParam(value = "openid")String openid,
                                          @RequestParam(value = "orderId")String orderId){
        buyerService.cancel(openid,orderId);
        return ResultUtils.success();
    }



}
