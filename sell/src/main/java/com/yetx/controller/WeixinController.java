package com.yetx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
public class WeixinController {


    @GetMapping("/auth")
    public void auth(@RequestParam String code){
        System.out.println("进入了auth方法");
        System.out.println("code"+code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx6c1554c95292e6aa&secret=0f960692df84df529bde45d625cb6761&code="+code+"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(url,String.class);

        System.out.println("response:"+response);

    }
}
