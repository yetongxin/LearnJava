package com.yetx.service.impl;

import com.yetx.daoobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductCategoryServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(ProductCategoryServiceImplTest.class);

    @Autowired
    private ProductCategoryServiceImpl service;
    @Test
    public void findOne() {
        ProductCategory category = service.findOne(3);
        Assert.assertNotNull(category);
        Assert.assertEquals(new Integer(2),category.getCategoryType());
        logger.info("id为3:{}",category.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list =service.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = service.findByCategoryTypeIn(Arrays.asList(1,3,4));
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() {
        ProductCategory category = new ProductCategory(123,"男默女泪",123);
        ProductCategory category1 = service.save(category);
        Assert.assertEquals(category.getCategoryType(),category1.getCategoryType());
    }
}