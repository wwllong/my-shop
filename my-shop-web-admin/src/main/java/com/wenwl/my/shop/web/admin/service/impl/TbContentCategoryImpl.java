package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.domain.entity.TbContentCategory;
import com.wenwl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.wenwl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wenwl
 * @className TbContentCategoryImpl
 * @dsecription 内容分类服务实现
 * @data 2020/2/16 0016
 * @vserion 1.0.0
 */
@Service
public class TbContentCategoryImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    /**
     * 列表查询
     *
     * @return
     */
    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }

}
