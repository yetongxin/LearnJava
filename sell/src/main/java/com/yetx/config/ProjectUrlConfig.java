package com.yetx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {

    private String wechatMpAuthorize;

    private String wechatOpenAuthorize;

    private String sell;

    public String getWechatMpAuthorize() {
        return wechatMpAuthorize;
    }

    public void setWechatMpAuthorize(String wechatMpAuthorize) {
        this.wechatMpAuthorize = wechatMpAuthorize;
    }

    public String getWechatOpenAuthorize() {
        return wechatOpenAuthorize;
    }

    public void setWechatOpenAuthorize(String wechatOpenAuthorize) {
        this.wechatOpenAuthorize = wechatOpenAuthorize;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}
