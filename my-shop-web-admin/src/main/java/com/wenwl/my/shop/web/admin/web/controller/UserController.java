package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author wenwl
 * @className UserController
 * @data 2020/1/30
 * @vserion 1.0.0
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService userService;

    @GetMapping(value = "/list")
    public String getList(Model model){
        List<TbUser> tbUsers = userService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

}
