package com.wenwl.my.shop.web.api.service;

import com.wenwl.my.shop.domain.entity.TbContent;

import java.util.List;

public interface TbContentService {

    /**
     * 根据类别 ID 查询内容列表
     * @param categoryId
     * @return
     */
    List<TbContent> getByCategoryId(Long categoryId);
}
