package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.commons.utils.BeanValidator;
import com.wenwl.my.shop.commons.utils.RegexpUtils;
import com.wenwl.my.shop.domain.entity.TbContent;
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
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    /**
     * 保存内容信息
     *
     * @param tbContent
     * @return
     */
    @Override
    public BaseResult save(TbContent tbContent) {

        String validator = BeanValidator.validator(tbContent);
        //验证不通过
        if(validator != null){
            return BaseResult.fail(validator);
        }
        //验证通过
        else{
            tbContent.setUpdated(new Date());
            //新增内容
            if(tbContent.getId() == null){
                tbContent.setCreated(new Date());
                tbContentDao.insert(tbContent);
            }
            //编辑内容
            else{
                tbContentDao.update(tbContent);
            }
            return BaseResult.success("保存内容信息成功");
        }

    }

    /**
     * 删除内容
     *
     * @param tbContent
     * @return
     */
    @Override
    public int delete(TbContent tbContent) {
        return tbContentDao.delete(tbContent);
    }

    /**
     * 查询内容
     *
     * @param id
     * @return
     */
    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public long batchDelete(String[] ids) {
        return tbContentDao.batchDelete(ids);
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageInfo<TbContent> page(Map<String, Object> params) {
        PageInfo<TbContent> pageInfo = new PageInfo<>();

        int count = tbContentDao.count((TbContent)params.get("tbContent"));
        List<TbContent> tbContents = tbContentDao.page(params);

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbContents);

        return pageInfo;
    }

    /**
     * 统计总记录数
     *
     * @return
     */
    @Override
    public int count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }

    /**
     * 根据内容类目ID删除
     *
     * @param categoryIds
     */
    @Override
    public int deleteByCategoryId(Long[] categoryIds) {
        return tbContentDao.deleteByCategoryId(categoryIds);
    }


}
