package com.yetx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

   private String myAppId;

   private String myAppSecret;

    public String getMyAppSecret() {
        return myAppSecret;
    }

    public void setMyAppSecret(String myAppSecret) {
        this.myAppSecret = myAppSecret;
    }

    public String getMyAppId() {
        return myAppId;
    }

    public void setMyAppId(String myAppId) {
        this.myAppId = myAppId;
    }
}
