package com.yetx.service.impl;

import com.yetx.daoobject.ProductInfo;
import com.yetx.dto.CartDTO;
import com.yetx.enums.ProductStatusEnums;
import com.yetx.enums.SellResultEnums;
import com.yetx.exception.SellException;
import com.yetx.repository.ProductInfoRepository;
import com.yetx.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    private final Logger logger = LoggerFactory.getLogger(ProductInfoServiceImpl.class);
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo findById(String ProductId) {
        return repository.findById(ProductId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpALL() {
        return repository.findByProductStatus(ProductStatusEnums.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
//    下面3个方法还未重写
    @Override
    public boolean deleteProduct(String ProductId) {
        return false;
    }

    @Override
    public void upProduct(String ProductId) {
        ProductInfo productInfo = findById(ProductId);
        if(productInfo==null){
            logger.error("id为{}的商品不存在",ProductId);
            throw new SellException(SellResultEnums.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus()!=ProductStatusEnums.DOWN.getCode()){
            logger.error("id为{}的商品状态不正确",ProductId);
            throw new SellException(SellResultEnums.PRODUCT_UPLOAD_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnums.UP.getCode());
        repository.save(productInfo);
    }

    @Override
    public void downProduct(String ProductId) {
        ProductInfo productInfo = findById(ProductId);
        if(productInfo==null){
            logger.error("id为{}的商品不存在",ProductId);
            throw new SellException(SellResultEnums.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus()!=ProductStatusEnums.UP.getCode()){
            logger.error("id为{}的商品状态不正确",ProductId);
            throw new SellException(SellResultEnums.PRODUCT_DOWNLOAD_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnums.DOWN.getCode());
        repository.save(productInfo);

    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        Integer curStock;
        for(CartDTO cart:cartDTOList){
            ProductInfo product =  findById(cart.getProductId());
            curStock =  product.getProductStock();
            curStock -= cart.getProductQuantity();
            if(curStock<0){
                throw new SellException(SellResultEnums.PRODUCT_STOCK_ERROR);
            }
            product.setProductStock(curStock);
            repository.save(product);
        }
    }

    @Override
    public void addStock(List<CartDTO> cartDTOList) {
        Integer curStock;
        for(CartDTO cart:cartDTOList){
            ProductInfo product =  findById(cart.getProductId());
            curStock =  product.getProductStock();
            curStock += cart.getProductQuantity();
//            if(curStock<0){
//                throw new SellException(SellResultEnums.PRODUCT_STOCK_ERROR);
//            }
            product.setProductStock(curStock);
            repository.save(product);
        }
    }

}
