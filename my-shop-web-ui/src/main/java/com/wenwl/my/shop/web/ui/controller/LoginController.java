package com.wenwl.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wenwl
 * @className LoginController
 * @dsecription 登陆控制器
 * @data 2020/4/9 23:37
 * @vserion 1.0.0
 */
@Controller
public class LoginController {

    /**
     * 跳转登录页
     * @return
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }
}
