package com.yetx.repository;

import com.yetx.daoobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    //public Page<OrderMaster> findByOrderId(Integer orderId, Pageable pageable);
    public Page<OrderMaster> findByBuyerOpenid(String BuyerOpenId, Pageable pageable);
}
