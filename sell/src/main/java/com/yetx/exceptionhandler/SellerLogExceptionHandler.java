package com.yetx.exceptionhandler;

import com.yetx.config.ProjectUrlConfig;
import com.yetx.exception.SellerAthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SellerLogExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @ExceptionHandler(value = SellerAthorizeException.class)
    public String handlerAthorizeException(){

        //return "redirect:http://localhost:8080/sell/page/login.html";
        //return "redirect:/sell/page/login.html";
        //return "redirect:http://sellsell.natapp1.cc/sell/page/login.html";
        return "redirect:".concat(projectUrlConfig.getSell())
                          .concat("/sell/page/login.html");
    }
}
