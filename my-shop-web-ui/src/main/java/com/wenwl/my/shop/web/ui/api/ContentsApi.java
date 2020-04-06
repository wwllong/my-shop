package com.wenwl.my.shop.web.ui.api;

import com.wenwl.my.shop.commons.utils.HttpClientUtils;
import com.wenwl.my.shop.commons.utils.JacksonUtils;
import com.wenwl.my.shop.web.ui.dto.TbContentDTO;

import java.util.List;

/**
 * @author wenwl
 * @className ContentsApi
 * @dsecription 内容管理接口
 * @data 2020/4/6 15:49
 * @vserion 1.0.0
 */
public class ContentsApi {

    /**
     * 首页轮播图
     * @return
     */
    public static List<TbContentDTO> indexBanner(){
        List<TbContentDTO> tbContents = null;
        String result = HttpClientUtils.doGet(Api.API_CONTENTS + 89);
        try {
            tbContents = JacksonUtils.json2listByTree(result, "data", TbContentDTO.class);
            System.out.println(tbContents);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }

}
