package com.wenwl.my.shop.web.api.dao;

import com.wenwl.my.shop.domain.entity.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wenwl
 * @className TbContentDao
 * @dsecription 内容数据访问对象
 * @data 2020/3/29
 * @vserion 1.0.0
 */
@Repository
public interface TbContentDao {

    /**
     * 根据类别 ID 查询内容列表
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCategoryId(TbContent tbContent);

}
