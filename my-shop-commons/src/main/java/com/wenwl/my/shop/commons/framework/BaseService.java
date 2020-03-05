package com.wenwl.my.shop.commons.framework;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.commons.persistence.BaseEntity;

import java.util.Map;

/**
 * @author wenwl
 * @className BaseService
 * @dsecription 服务层接口基类
 * @data 2020/3/6 0:15
 * @vserion 1.0.0
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 保存
     * @return
     */
    public BaseResult save(T entity);

    /**
     * 删除
     * @param entity
     * @return
     */
    public int delete(T entity);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String[] ids);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T getById(Long id);

    /**
     * 分页查询
     * @param params
     * @return
     */
    public PageInfo<T> page(Map<String, Object> params);

    /**
     * 统计总记录数
     * @return
     */
    public int count(T entity);

}
