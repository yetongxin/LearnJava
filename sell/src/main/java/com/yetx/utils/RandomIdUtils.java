package com.yetx.utils;

public class RandomIdUtils {

    public static synchronized String getRandomId(){
        Integer num = (int)(Math.random()*900000+10000);
        return String.valueOf(System.currentTimeMillis())+num;
    }
}
