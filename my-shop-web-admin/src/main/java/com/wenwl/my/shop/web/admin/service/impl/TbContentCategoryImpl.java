package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.utils.BeanValidator;
import com.wenwl.my.shop.domain.entity.TbContentCategory;
import com.wenwl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.wenwl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 保存内容分类
     *
     * @param tbContentCategory
     * @return
     */
    @Override
    public BaseResult save(TbContentCategory tbContentCategory) {
        String validator = BeanValidator.validator(tbContentCategory);
        // 验证不通过
        if(validator != null){
            return BaseResult.fail(validator);
        }
        // 验证通过
        else{
            tbContentCategory.setUpdated(new Date());
            //新增内容分类
            if(tbContentCategory.getId() == null){
                tbContentCategoryDao.insert(tbContentCategory);
            }
            //编辑内容分类
            else{
                tbContentCategoryDao.update(tbContentCategory);
            }
            return BaseResult.success("保存内容分类成功");
        }
    }

    /**
     * 根据父节点ID返回列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<TbContentCategory> selectByPid(Long parentId) {
        return tbContentCategoryDao.selectByPid(parentId);
    }

    /**
     * 查询内容分类
     *
     * @param id
     * @return
     */
    @Override
    public TbContentCategory getById(Long id) {
        return tbContentCategoryDao.getById(id);
    }

}
