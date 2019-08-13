package com.yetx.service.impl;

import com.yetx.daoobject.ProductCategory;
import com.yetx.repository.ProductCategoryRepository;
import com.yetx.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer CategoryId) {
        return repository.findById(CategoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypes) {
        return repository.findByCategoryTypeIn(CategoryTypes);

    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return repository.save(category);
    }


}
