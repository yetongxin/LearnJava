package com.yetx.utils;

import com.yetx.enums.ListResultEnum;
import com.yetx.viewobject.ResultVO;

public class ResultUtils {
    public static <T> ResultVO<T> success(T data){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ListResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ListResultEnum.SUCCESS.getMsg());
        resultVO.setData(data);
        return  resultVO;
    }
    public static <T> ResultVO<T> success(){
        return success(null);
    }
    public static <T> ResultVO<T> fail(){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ListResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ListResultEnum.SUCCESS.getMsg());
        resultVO.setData(null);
        return resultVO;
    }
}
