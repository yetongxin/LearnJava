package com.yetx.service;

import com.yetx.daoobject.ProductInfo;
import com.yetx.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    //public List<ProductInfo> findAll();

    public ProductInfo findById(String ProductId);
    /**
     * 查询在架
     * @return
     */
    public List<ProductInfo> findUpALL();

    public Page<ProductInfo> findAll(Pageable pageable);
    public ProductInfo save(ProductInfo productInfo);

    /**
     * 根据传入id删除商品
     * @param ProductId
     * @return
     */
    public boolean deleteProduct(String ProductId);

    /**
     * 上架商品
     * @param ProductId
     */
    public void upProduct(String ProductId);

    /**
     * 下架商品
     * @param ProductId
     */
    public  void downProduct(String ProductId);

    /** 将ProductId num封装起来为Cart*/
    public void decreaseStock(List<CartDTO> cartDTOList);

    public void addStock(List<CartDTO> cartDTOList);
}
