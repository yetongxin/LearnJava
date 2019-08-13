package com.yetx.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatMpConfig {

    @Autowired
    private WechatAccountConfig accountConfig;
    @Bean
    public WxMpService WxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(WxMpConfigStorage());
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage WxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(accountConfig.getMyAppId());
        wxMpConfigStorage.setSecret(accountConfig.getMyAppSecret());
        return wxMpConfigStorage;
    }

}
