package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.commons.utils.BeanValidator;
import com.wenwl.my.shop.commons.utils.RegexpUtils;
import com.wenwl.my.shop.domain.entity.TbContent;
import com.wenwl.my.shop.web.admin.commons.framework.AbstractServiceImpl;
import com.wenwl.my.shop.web.admin.dao.TbContentDao;
import com.wenwl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wenwl
 * @className TbContentServiceImpl
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Service
public class TbContentServiceImpl extends AbstractServiceImpl<TbContentDao, TbContent> implements TbContentService {

    /**
     * 根据内容类目ID删除
     *
     * @param categoryIds
     */
    @Override
    public int deleteByCategoryId(String[] categoryIds) {
        return baseDao.deleteByCategoryId(categoryIds);
    }


}
