package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.commons.persistence.BaseDao;
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
public interface TbContentDao extends BaseDao<TbContent> {

    /**
     * 根据内容类目ID删除
     * @param categoryIds
     * @return
     */
    public int deleteByCategoryId(String[] categoryIds);

}
