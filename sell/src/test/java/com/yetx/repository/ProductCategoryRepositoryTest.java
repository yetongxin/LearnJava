package com.yetx.repository;

import com.yetx.daoobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(ProductCategoryRepositoryTest.class);

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void testCategoryRepository(){
        ProductCategory category = new ProductCategory();
        category.setCategoryName("热门系列2");
        category.setCategoryType(4);
        category.setCategoryId(4);
        repository.save(category);
        Assert.assertNotEquals(0,repository.count());
//        repository.findById(1);
        Assert.assertNotNull(repository.findById(1));

    }
    @Test
    public void testFindOne(){
       ProductCategory category = new ProductCategory();
       //orElse如果Option中存在值则返回这个值，否则返回传入的参数
       logger.info(repository.findById(3).orElse(category).toString());

    }
    @Test
    public void testSave(){
        ProductCategory category = new ProductCategory(5,"男生热榜",10);
        repository.save(category);
        Assert.assertNotNull(repository.findById(5).orElse(null));
        logger.info(repository.findById(5).orElse(null).toString());

    }
    @Test
    public void testUpdate(){
        logger.info("update-start-------------------------");
        ProductCategory category = repository.findById(5).orElse(null);
        logger.info(category.toString());
        category.setCategoryType(16);
        logger.info(category.toString());
        repository.save(category);

        //.assertNotNull(repository.findById(5).orElse(null));
        logger.info(repository.findById(5).orElse(null).toString());

    }

    @Test
    public void testgetByTypes(){
        logger.info("testgetByTypes----------------------------------");
        List<ProductCategory> category = repository.findByCategoryTypeIn(Arrays.asList(1,3,4));

        Assert.assertEquals(3,category.size());
        logger.info("testgetByTypes的size为{}",category.size());
    }
}