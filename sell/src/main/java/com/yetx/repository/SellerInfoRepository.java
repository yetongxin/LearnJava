package com.yetx.repository;

import com.yetx.daoobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    public SellerInfo findBySellerId(String id);
}
