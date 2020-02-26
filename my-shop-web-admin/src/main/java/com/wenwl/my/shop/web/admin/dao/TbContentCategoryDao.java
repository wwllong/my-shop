package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.domain.entity.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wenwl
 * @className TbContentCategoryDao
 * @dsecription 内容分类数据访问对象
 * @data 2020/2/16
 * @vserion 1.0.0
 */
@Repository
public interface TbContentCategoryDao {

    /**
     * 插入内容分类
     * @return
     */
    public int insert(TbContentCategory tbContentCategory);

    /**
     * 更新内容分类
     * @param tbContentCategory
     * @return
     */
    public int update(TbContentCategory tbContentCategory);

    /**
     * 查询列表
     * @return
     */
    public List<TbContentCategory> selectAll();

    /**
     * 根据父节点ID返回列表
     * @return
     */
    public List<TbContentCategory> selectByPid(Long parentId);

    /**
     * 根据id查询内容分类
     * @return
     */
    public TbContentCategory getById(Long id);

}
