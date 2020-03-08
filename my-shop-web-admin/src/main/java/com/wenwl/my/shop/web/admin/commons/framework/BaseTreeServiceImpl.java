package com.wenwl.my.shop.web.admin.commons.framework;

import com.wenwl.my.shop.commons.framework.BaseDao;
import com.wenwl.my.shop.commons.framework.BaseTreeDao;
import com.wenwl.my.shop.commons.framework.BaseTreeService;
import com.wenwl.my.shop.commons.persistence.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wenwl
 * @className BaseTreeServiceImpl
 * @dsecription 树服务实现基类
 * @data 2020/3/6 1:12
 * @vserion 1.0.0
 */
public abstract class BaseTreeServiceImpl<D extends BaseTreeDao<T>, T extends BaseEntity> implements BaseTreeService<T> {

    @Autowired
    protected D baseDao;

    /**
     * 查询所有数据
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return baseDao.selectAll();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id){
        return baseDao.batchDelete(new String[]{String.valueOf(id)});
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return baseDao.getById(id);
    }

    /**
     * 根据父节点ID返回列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<T> selectByPid(Long parentId) {
        return baseDao.selectByPid(parentId);
    }

    /**
     * 查找所有子节点
     * @param targetList
     * @param parentId
     */
    @Override
    public void findAllChild(List<Long> targetList, Long parentId) {
        targetList.add(parentId);

        List<T> entityList = selectByPid(parentId);
        for(T entity : entityList){
            findAllChild(targetList, entity.getId());
        }
    }

}
