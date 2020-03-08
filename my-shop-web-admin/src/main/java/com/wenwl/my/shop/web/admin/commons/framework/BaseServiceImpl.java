package com.wenwl.my.shop.web.admin.commons.framework;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.commons.framework.BaseDao;
import com.wenwl.my.shop.commons.framework.BaseService;
import com.wenwl.my.shop.commons.persistence.BaseEntity;
import com.wenwl.my.shop.commons.utils.BeanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wenwl
 * @className BaseServiceImpl
 * @dsecription 服务实现基类
 * @data 2020/3/6 0:36
 * @vserion 1.0.0
 */
public abstract class BaseServiceImpl<D extends BaseDao<T>,T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected D baseDao;

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(T entity) {

        String validator = BeanValidator.validator(entity);
        //验证不通过
        if(validator != null){
            return BaseResult.fail(validator);
        }
        //验证通过
        else{
            entity.setUpdated(new Date());
            //新增
            if(entity.getId() == null){
                entity.setCreated(new Date());
                baseDao.insert(entity);
            }
            //编辑
            else{
                baseDao.update(entity);
            }
            return BaseResult.success("保存成功!");
        }

    }

    /**
     * 删除
     *
     * @param entity
     * @return
     */
    @Override
    public int delete(T entity) {
        return baseDao.delete(entity.getId());
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public int batchDelete(String[] ids) {
        return baseDao.batchDelete(ids);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return baseDao.getById(id);
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageInfo<T> page(Map<String, Object> params) {
        PageInfo<T> pageInfo = new PageInfo<>();

        int count = baseDao.count((T)params.get("pageParams"));
        List<T> entitys = baseDao.page(params);

        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(entitys);

        return pageInfo;
    }

    /**
     * 统计总记录数
     *
     * @return
     */
    @Override
    public int count(T entity) {
        return baseDao.count(entity);
    }

}
