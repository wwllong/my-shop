package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.domain.entity.TbContent;
import com.wenwl.my.shop.web.admin.commons.framework.BaseServiceImpl;
import com.wenwl.my.shop.web.admin.dao.TbContentDao;
import com.wenwl.my.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wenwl
 * @className TbContentServiceImpl
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl extends BaseServiceImpl<TbContentDao, TbContent> implements TbContentService {

    /**
     * 根据内容类目ID删除
     *
     * @param categoryIds
     */
    @Override
    @Transactional(readOnly = false)
    public int deleteByCategoryId(String[] categoryIds) {
        return baseDao.deleteByCategoryId(categoryIds);
    }


}
