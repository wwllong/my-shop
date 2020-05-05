package com.wenwl.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wenwl
 * @className RegisterController
 * @dsecription 注册控制器
 * @data 2020/4/9 23:40
 * @vserion 1.0.0
 */
@Controller
public class RegisterController {

    /**
     * 跳转注册页
     * @return
     */
    @GetMapping("register")
    public String register(){
        return "register";
    }



}
