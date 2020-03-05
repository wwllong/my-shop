package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.domain.entity.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wenwl
 * @className TbContentDao
 * @dsecription 内容数据访问对象
 * @data 2020/2/20
 * @vserion 1.0.0
 */
@Repository
public interface TbContentDao {

    /**
     * 查询所有列表
     */
    public List<TbContent> selectAll();

    /**
     * 插入一条内容信息
     * @return
     */
    public int insert(TbContent tbContent);

    /**
     * 删除内容Id
     */
    public int delete(TbContent tbContent);

    /**
     * 查询单个对象
     * @param id
     * @return
     */
    public TbContent getById(Long id);

    /**
     * 更新
     * @param tbContent
     * @return
     */
    public int update(TbContent tbContent);

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
    public List<TbContent> page(Map<String, Object> params);

    /**
     * 统计总记录数
     * @return
     */
    public int count(TbContent tbContent);

    /**
     * 根据内容类目ID删除
     * @param categoryIds
     * @return
     */
    public int deleteByCategoryId(Long[] categoryIds);
}
