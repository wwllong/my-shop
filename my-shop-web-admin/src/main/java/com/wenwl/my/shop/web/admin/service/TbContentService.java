package com.wenwl.my.shop.web.admin.service;

import com.wenwl.my.shop.commons.framework.BaseService;
import com.wenwl.my.shop.domain.entity.TbContent;

/**
 * @author wenwl
 * @className TbContentService
 * @data 2020/1/16
 * @vserion 1.0.0
 */
public interface TbContentService extends BaseService<TbContent> {

    /**
     * 根据内容类目ID删除
     * @param categoryIds
     */
    public int deleteByCategoryId(String[] categoryIds);

}
