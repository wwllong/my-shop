package com.wenwl.my.shop.web.ui.controller;

import com.wenwl.my.shop.web.ui.api.ContentsApi;
import com.wenwl.my.shop.web.ui.dto.TbContentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
    public String index(Model model){
        // 请求首页轮播广告
        requestContentsIndexCarousel(model);
        return "index";
    }

    /**
     * 请求首页轮播广告
     * @param model
     */
    private void requestContentsIndexCarousel(Model model) {
        List<TbContentDTO> tbContentDTOS = ContentsApi.indexBanner();
        model.addAttribute("banner",tbContentDTOS);
    }

}
