package com.wenwl.my.shop.commons.framework;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.persistence.BaseEntity;

import java.util.List;

/**
 * @author wenwl
 * @className BaseTreeService
 * @dsecription 树结构数据服务层接口基类
 * @data 2020/3/6 0:23
 * @vserion 1.0.0
 */
public interface BaseTreeService<T extends BaseEntity> {

    /**
     * 保存
     * @return
     */
    public BaseResult save(T entity);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id);

    /**
     * 查询所有数据
     * @return
     */
    public List<T> selectAll();

    /**
     * 根据父节点ID返回列表
     * @return
     */
    public List<T> selectByPid(Long parentId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T getById(Long id);

}
