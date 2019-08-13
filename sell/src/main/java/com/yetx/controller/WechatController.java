package com.yetx.controller;

import com.yetx.config.ProjectUrlConfig;
import com.yetx.enums.SellResultEnums;
import com.yetx.exception.SellException;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private ProjectUrlConfig urlConfig;
    private final Logger logger = LoggerFactory.getLogger(WechatController.class);


    @GetMapping("/authorize")
    public String authorize(@RequestParam(value = "returnUrl") String returnUrl){
        String url = urlConfig.getWechatMpAuthorize()+"/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        return "redirect:"+ redirectUrl;
    }

    //用户同意授权后即被重定向到该方法对应的url,并带有两个参数,code,state
    //如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl){
        //获取code后，请求以下链接获取access_token：
        // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            logger.error("【微信授权出错】:{}",e);
            throw new SellException(SellResultEnums.WECHAT_MP_ERROR);
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openid;
    }
}
