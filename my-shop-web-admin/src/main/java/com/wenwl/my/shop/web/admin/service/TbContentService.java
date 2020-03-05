package com.wenwl.my.shop.web.admin.service;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.domain.entity.TbContent;

import java.util.Map;

/**
 * @author wenwl
 * @className TbContentService
 * @data 2020/1/16
 * @vserion 1.0.0
 */
public interface TbContentService {

    /**
     * 保存内容信息
     * @return
     */
    public BaseResult save(TbContent tbContent);

    /**
     * 删除内容
     * @param tbContent
     * @return
     */
    public int delete(TbContent tbContent);

    /**
     * 查询内容
     * @param id
     * @return
     */
    public TbContent getById(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    long batchDelete(String[] ids);

    /**
     * 分页查询
     * @param params
     * @return
     */
    public PageInfo<TbContent> page(Map<String, Object> params);

    /**
     * 统计总记录数
     * @return
     */
    public int count(TbContent tbContent);

    /**
     * 根据内容类目ID删除
     * @param categoryIds
     */
    public int deleteByCategoryId(Long[] categoryIds);
}
