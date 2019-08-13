package com.yetx.controller;

import com.yetx.config.ProjectUrlConfig;
import com.yetx.constant.CookieConstant;
import com.yetx.constant.RedisConstant;
import com.yetx.daoobject.SellerInfo;
import com.yetx.enums.SellResultEnums;
import com.yetx.exception.SellException;
import com.yetx.service.SellerInfoService;
import com.yetx.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerLogController {


    private Logger logger = LoggerFactory.getLogger(SellerLogController.class);
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @RequestMapping("/login")
    public String login(@RequestParam(value = "sellerId") String sellerId,
                        @RequestParam(value = "password") String password,
                        HttpServletResponse response,
                        HttpServletRequest request){
        //redisTemplate.opsForValue().set("abcd","1234",RedisConstant.EXPIRE, TimeUnit.SECONDS);
        SellerInfo sellerInfo = sellerInfoService.findSellerById(sellerId);
        if(sellerInfo==null){
            logger.error("【登录失败】：用户不存在");
            throw new SellException(SellResultEnums.SELLER_NOT_EXIST);
        }
        else{
            if(!sellerInfo.getPassword().equals(password)){
                logger.error("【登录失败】：密码错误");
                throw  new SellException(SellResultEnums.SELLER_PASSWORD_ERROR);
            }
            else {
                //用户名密码正确，接下来存储信息到cookie与redis

                //1.产生一个token,客户端：(这名字可随意)token->(随机生成的)key,服务器：(加密后的)key->value
                String Token = UUID.randomUUID().toString();

                //2.客户端，设置一个cookie，用于向后台取数据
                CookieUtils.setCookie(CookieConstant.TOKEN,Token,CookieConstant.EXPIRE,response);

                //3.服务器，存储到redis,代表该用于已经登录了
                redisTemplate.opsForValue().set(String.format(RedisConstant.PREFIX,Token),sellerId,RedisConstant.EXPIRE, TimeUnit.SECONDS);
                logger.info("存储完成！");
                return "redirect:".concat(projectUrlConfig.getSell()).concat("/sell/seller/order/list");
            }
        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout(Map<String,Object> map,
                               HttpServletResponse response,
                               HttpServletRequest request){
        Cookie cookie = CookieUtils.getCookie(CookieConstant.TOKEN,request);
        redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.PREFIX,cookie.getValue()));
        CookieUtils.setCookie(CookieConstant.TOKEN,"",0,response);
        map.put("url","/sell/seller/order/list");
        map.put("msg",SellResultEnums.SELLER_LOGOUT_SUCCESS.getMsg());
        return new ModelAndView("common/success");
    }
}
