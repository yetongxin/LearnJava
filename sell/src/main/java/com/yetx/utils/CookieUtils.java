package com.yetx.utils;

import com.yetx.constant.CookieConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static Cookie getCookie(String name,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies==null)   return null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(CookieConstant.TOKEN)){
               return cookie;
            }
        }
        return null;
    }
    public static void setCookie(String name, String value, Integer maxage, HttpServletResponse response){

        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(maxage);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
