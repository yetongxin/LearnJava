package com.yetx.repository;

import com.yetx.daoobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
}
