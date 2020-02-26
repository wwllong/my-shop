package com.wenwl.my.shop.web.admin.service;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbContentCategory;

import java.util.List;

/**
 * @author wenwl
 * @className TbContentCategory
 * @dsecription 内容分类服务
 * @data 2020/2/16
 * @vserion 1.0.0
 */
public interface TbContentCategoryService {

    /**
     * 列表查询
     * @return
     */
    public List<TbContentCategory> selectAll();

    /**
     * 保存内容分类
     * @return
     */
    public BaseResult save(TbContentCategory tbContentCategory);

    /**
     * 根据父节点ID返回列表
     * @return
     */
    public List<TbContentCategory> selectByPid(Long parentId);

    /**
     * 查询内容分类
     * @param id
     * @return
     */
    public TbContentCategory getById(Long id);


}
