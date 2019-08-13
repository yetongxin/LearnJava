package com.yetx.service;

import com.yetx.daoobject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCategoryService {

    public ProductCategory findOne(Integer CategoryId);

    public List<ProductCategory> findAll();

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypes);

    public ProductCategory save(ProductCategory category);


}
