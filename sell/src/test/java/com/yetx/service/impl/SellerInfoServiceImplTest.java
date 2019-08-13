package com.yetx.service.impl;

import com.yetx.daoobject.SellerInfo;
import com.yetx.service.SellerInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoService sellerInfoService;

    private Logger logger = LoggerFactory.getLogger(SellerInfoServiceImplTest.class);
    @Test
    public void findSellerById() {
        SellerInfo sellerInfo = sellerInfoService.findSellerById("000");
        logger.info("sellerInfo:{}",sellerInfo);
        Assert.assertNull(sellerInfo);
        //Assert.assertEquals("admin",sellerInfo.getPassword());
    }
    @Test
    public void saveTest(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId("1232134");
        sellerInfo.setUsername("abcdefg");
        sellerInfo.setPassword("12345666");
        SellerInfo sellerInfores = sellerInfoService.saveSeller(sellerInfo);
        Assert.assertNotNull(sellerInfores);

    }
}