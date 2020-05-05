package com.wenwl.my.shop.web.api.service.impl;

import com.wenwl.my.shop.domain.entity.TbContent;
import com.wenwl.my.shop.domain.entity.TbContentCategory;
import com.wenwl.my.shop.web.api.dao.TbContentDao;
import com.wenwl.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> getByCategoryId(Long categoryId) {

        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);

        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);

        return tbContentDao.selectByCategoryId(tbContent);

    }
}
