package com.yetx.service;

import com.yetx.daoobject.SellerInfo;

public interface SellerInfoService {
    public SellerInfo findSellerById(String sellerId);

    public SellerInfo saveSeller(SellerInfo sellerInfo);
}
