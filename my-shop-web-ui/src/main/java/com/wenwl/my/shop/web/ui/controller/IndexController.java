package com.wenwl.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wenwl
 * @className IndexController
 * @dsecription 门户首页
 * @data 2020/4/1 1:18
 * @vserion 1.0.0
 */
@Controller
public class IndexController {

    @GetMapping(value = {"","index"})
    public String index(){
        return "index";
    }

}
