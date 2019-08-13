package com.yetx.utils;

import com.yetx.enums.CodeEnum;

public class EnumUtils {
    //为了能获取到T类型的code，必须知道T是某一个含有getCode()方法的类的实现类（或者子类）
    //因此可设计一个CodeEnum接口
    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for(T one:enumClass.getEnumConstants()){
            if(one.getCode()==code){
                return one;
            }
        }
        return null;
    }
}
