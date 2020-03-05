package com.wenwl.my.shop.commons.framework;

import com.wenwl.my.shop.commons.persistence.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author wenwl
 * @className BaseDao
 * @dsecription 数据访问层基类
 * @data 2020/3/5 0:22
 * @vserion 1.0.0
 */
public interface BaseDao<T extends BaseEntity> {

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
     * 删除
     */
    public int delete(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int batchDelete(String[] ids);

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    public T getById(Long id);

    /**
     * 查询所有数据
     */
    public List<T> selectAll();

    /**
     * 分页查询
     * @param params
     * @return
     */
    public List<T> page(Map<String,Object> params);

    /**
     * 统计总记录数
     * @return
     */
    public int count(T entity);

}
