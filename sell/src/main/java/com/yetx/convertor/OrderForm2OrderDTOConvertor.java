package com.yetx.convertor;

import com.google.gson.Gson;
import com.yetx.daoobject.OrderDetail;
import com.yetx.dto.OrderDTO;
import com.yetx.formobject.OrderForm;
import com.yetx.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class OrderForm2OrderDTOConvertor {
    /*name: "张三"
        phone: "18868822111"
        address: "慕课网总部"
        openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
        items: [{
            productId: "1423113435324",
                    productQuantity: 2 //购买数量
        }]*/
    private static Gson gson = new Gson();
    private static OrderDetailRepository orderDetailRepo;

    @Autowired//静态注入
    public void setOrderDetailRepo(OrderDetailRepository orderDetailRepo2){
        OrderForm2OrderDTOConvertor.orderDetailRepo = orderDetailRepo2;
    }
    public static OrderDTO OrderForm2OrderDTO(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
//        orderDTO.setOrderId();不用设置，由service来设置就行
        List<OrderDetail> orderDetailList
                = orderForm.getItems().stream()
                .map(cartDTO -> {//不需要全填，只需要传给service一个id和数量，其他全由service获取，这里只是与前端的数据对象交互
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProductQuantity(cartDTO.getProductQuantity());
                    orderDetail.setProductId(cartDTO.getProductId());
                    return orderDetail;
                }).collect(Collectors.toList());
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
