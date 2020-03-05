package com.wenwl.my.shop.commons.persistence;

import java.util.List;

/**
 * @author wenwl
 * @className BaseTreeDao
 * @dsecription 树结构数据访问层基类
 * @data 2020/3/5 22:46
 * @vserion 1.0.0
 */
public interface BaseTreeDao<T extends BaseEntity> {

    /**
     * 新增
     * @return
     */
    public int insert(T entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * 批量删除
     * @return
     */
    public int batchDelete(String[] ids);

    /**
     * 根据id查询内容分类
     * @return
     */
    public T getById(Long id);

    /**
     * 查询列表
     * @return
     */
    public List<T> selectAll();

    /**
     * 根据父节点ID返回列表
     * @return
     */
    public List<T> selectByPid(Long parentId);

}
