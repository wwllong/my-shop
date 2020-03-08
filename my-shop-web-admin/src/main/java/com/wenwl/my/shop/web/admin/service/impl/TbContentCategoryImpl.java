package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.utils.BeanValidator;
import com.wenwl.my.shop.domain.entity.TbContentCategory;
import com.wenwl.my.shop.web.admin.commons.framework.BaseTreeServiceImpl;
import com.wenwl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.wenwl.my.shop.web.admin.service.TbContentCategoryService;
import com.wenwl.my.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@Transactional(readOnly = true)
public class TbContentCategoryImpl extends BaseTreeServiceImpl<TbContentCategoryDao, TbContentCategory> implements TbContentCategoryService {

    @Autowired
    private TbContentService tbContentService;

    /**
     * 保存内容分类
     *
     * @param tbContentCategory
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory tbContentCategory) {
        String validator = BeanValidator.validator(tbContentCategory);
        // 验证不通过
        if(validator != null){
            return BaseResult.fail(validator);
        }
        // 验证通过
        else{
            TbContentCategory parent = tbContentCategory.getParent();
            if(parent.getId() == null){
                // 说明当前节点是个根节点
                parent.setId(0L);
            }
            tbContentCategory.setUpdated(new Date());
            //新增内容分类
            if(tbContentCategory.getId() == null){
                tbContentCategory.setCreated(new Date());
                // 判断当前节点是否为父节点
                if(parent.getId() == 0L){
                    tbContentCategory.setIsParent(true);
                }else{
                    tbContentCategory.setIsParent(false);
                    // 更新父节点的信息
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if(currentCategoryParent != null){
                        currentCategoryParent.setIsParent(true);
                        baseDao.update(currentCategoryParent);
                    }
                }

                baseDao.insert(tbContentCategory);
            }
            //编辑内容分类
            else{
                baseDao.update(tbContentCategory);
            }
            return BaseResult.success("保存内容分类成功");
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int delete(Long id) {
        // 找到所有子节点的id
        List<Long> targetList = new ArrayList();
        findAllChild(targetList, id);

        String[] categoryIds = targetList.toArray(new String[targetList.size()]);
        // 删除类目以及所有子类目
        baseDao.batchDelete(categoryIds);

        // 删除相关的内容
        tbContentService.deleteByCategoryId(categoryIds);
        return 1;
    }

}
