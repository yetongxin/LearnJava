package com.yetx.service.impl;

import com.yetx.daoobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductInfoServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(ProductInfoServiceImplTest.class);

    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productpage =  service.findAll(request);
        List<ProductInfo> list = productpage.getContent();
        for(ProductInfo p:list)
            logger.info(p.toString());
    }

    @Test
    public void findById() {
        ProductInfo product = service.findById("123456");
        Assert.assertNotNull(product);
        logger.info(product.toString());
    }

    @Test
    public void findUpALL() {
        List<ProductInfo> list = service.findUpALL();
        Assert.assertEquals(4,list.size());
       // logger.info(list.get(2).toString());
    }

    @Test
    public void save() {
        ProductInfo productInfo = service.findById("123456");
        productInfo.setProductDescription("这是已经更改了的描述");
        ProductInfo productInfo1 = service.save(productInfo);
        Assert.assertEquals("这是已经更改了的描述",productInfo1.getProductDescription());
        logger.info(productInfo1.toString());
    }

    @Test
    public void upload(){
        service.upProduct("123460");
        ProductInfo productInfo = service.findById("123460");
        logger.info("获取到的productInfo:{}",productInfo);
        Assert.assertEquals(new Integer(1),productInfo.getProductStatus());
    }
    @Test
    public void doanload(){
        service.downProduct("123456");
        ProductInfo productInfo = service.findById("123456");
        logger.info("获取到的productInfo:{}",productInfo);
        Assert.assertEquals(new Integer(0),productInfo.getProductStatus());
    }

}