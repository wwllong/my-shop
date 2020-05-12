package com.wenwl.my.shop.web.ui.controller;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.web.ui.api.UsersApi;
import com.wenwl.my.shop.web.ui.dto.TbUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wenwl
 * @className RegisterController
 * @dsecription 注册控制器
 * @data 2020/4/9 23:40
 * @vserion 1.0.0
 */
@Controller
public class RegisterController {

//    @Autowired
//    private EmailSendUtils emailSendUtils;

    /**
     * 跳转注册页
     * @return
     */
    @GetMapping("register")
    public String register(){
        return "register";
    }

    @PostMapping("register")
    public String register(TbUserDTO tbUser, Model model, HttpServletRequest request) throws Exception{

        // 注册
        BaseResult result = UsersApi.register(tbUser);
        if(result.getStatus() == BaseResult.STATUS_FAIL){
            model.addAttribute("baseResult", result);
            return "register";
        }
        // 注册成功，发送邮件
        else{
//            emailSendUtils.send("欢迎注册", String.format("用户 【%s】 欢迎入驻 MyShop", tbUser.getUsername()), tbUser.getEmail());
            model.addAttribute("baseResult", result);
            return "login";
        }
    }



}
