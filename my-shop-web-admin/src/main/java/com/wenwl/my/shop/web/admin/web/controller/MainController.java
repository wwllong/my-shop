package com.wenwl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wenwl
 * @className MainController
 * @dsecription 首页控制器
 * @data 2020/1/13
 * @vserion 1.0.0
 */
@Controller
public class MainController {

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }

}
