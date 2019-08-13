package com.yetx.aspect;

import com.yetx.constant.CookieConstant;
import com.yetx.constant.RedisConstant;
import com.yetx.exception.SellerAthorizeException;
import com.yetx.utils.CookieUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginAspect {

    private final Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut("execution(public * com.yetx.controller.Seller*.*(..))"+
    "&& !execution(public  * com.yetx.controller.SellerLogController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtils.getCookie(CookieConstant.TOKEN, request);
        if(cookie==null){
            logger.info("【登录校验】cookie中无token");
            throw new SellerAthorizeException();
        }
        String token = cookie.getValue();
        String res = redisTemplate.opsForValue().get(String.format(RedisConstant.PREFIX,token));
        if(StringUtils.isEmpty(res)){
            logger.info("【登录校验】redis中查不到token");
            throw new SellerAthorizeException();
        }
    }

}
