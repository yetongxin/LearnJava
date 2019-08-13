package com.yetx.service;

import com.yetx.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO 需要创建的orderDTO
     * @return 创建完成的OrderDTO
     */
    public OrderDTO createOrder(OrderDTO orderDTO);

    /**
     * 取消订单
     * @param orderDTO 取消的订单
     * @return 取消操作完成的OrderDTO
     */
    public OrderDTO cancelOrder(OrderDTO orderDTO);


    /**
     * 获取所有订单
     * @param pageable 根据传入的pageable实现类，进行分页
     * @return 已分页的OrderDTO的Page
     */
    public Page<OrderDTO> findAllOrder(Pageable pageable);

    /**
     * 根据用户id获取该用户的所有订单，分页
     * @param buyerOpenid 查询buyyerId
     * @param pageable 根据传入的pageable实现类，进行分页
     * @return 已分页的OrderDTO的List
     */
    public Page<OrderDTO> findBuyerAllOrder(String buyerOpenid, Pageable pageable);

    /**
     * 根据订单id获取订单
     * @param OrderId 订单id
     * @return OrderDTO
     */
    public OrderDTO findOneByOrderId(String OrderId);


    //public OrderDTO findOneOrder(String openid,String OrderId);
    /**
     * 支付订单
     * @param orderDTO  需要支付的orderDTO
     * @return 已支付完成的orderDTO
     */
    public OrderDTO paidOrder(OrderDTO orderDTO);

    /**
     * 完成订单
     * @param orderDTO  需要完成的orderDTO
     * @return 已完成的orderDTO
     */
    public  OrderDTO finishOrder(OrderDTO orderDTO);
}
