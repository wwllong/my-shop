package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.commons.framework.BaseTreeDao;
import com.wenwl.my.shop.domain.entity.TbContentCategory;
import org.springframework.stereotype.Repository;

/**
 * @author wenwl
 * @className TbContentCategoryDao
 * @dsecription 内容分类数据访问对象
 * @data 2020/2/16
 * @vserion 1.0.0
 */
@Repository
public interface TbContentCategoryDao extends BaseTreeDao<TbContentCategory> {

}
