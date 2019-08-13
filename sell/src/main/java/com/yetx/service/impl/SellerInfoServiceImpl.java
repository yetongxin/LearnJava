package com.yetx.service.impl;

import com.yetx.daoobject.SellerInfo;
import com.yetx.repository.SellerInfoRepository;
import com.yetx.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findSellerById(String sellerId) {
        return sellerInfoRepository.findBySellerId(sellerId);
    }

    @Override
    public SellerInfo saveSeller(SellerInfo sellerInfo) {
        return sellerInfoRepository.save(sellerInfo);
    }
}
