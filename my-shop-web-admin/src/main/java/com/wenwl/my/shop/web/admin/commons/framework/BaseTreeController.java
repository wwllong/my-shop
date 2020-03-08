package com.wenwl.my.shop.web.admin.commons.framework;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.framework.BaseTreeService;
import com.wenwl.my.shop.commons.persistence.BaseEntity;
import com.wenwl.my.shop.commons.persistence.BaseTreeEntity;
import com.wenwl.my.shop.domain.entity.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author wenwl
 * @className BaseTreeController
 * @dsecription 控制层实现基类(树类型数据）
 * @data 2020/3/8 20:51
 * @vserion 1.0.0
 */
public abstract class BaseTreeController <S extends BaseTreeService<T>, T extends BaseTreeEntity>  {

    @Autowired
    protected S baseService;

    /**
     * 回显数据
     * @param id
     * @return
     */
    @ModelAttribute
    public T getEntity(Long id){
        T entity = null;

        if(id != null){
            entity = baseService.getById(id);
        }
        else {
            entity = createEntity();
        }

        return entity;
    }

    /**
     * ModelAttribute-创建实体对象
     * @return
     */
    public abstract T createEntity();

    /**
     * 保存信息
     * @param entity
     * @param redirectAttributes
     * @param model
     * @return
     */
    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

    /**
     * 跳转列表页
     *
     * @return
     */
    public abstract String list(Model model);

    /**
     * 对列表进行排序-方便前端列表TreeTable显示
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId 父节点的 ID
     */
    protected void sortList(List<T> sourceList, List<T> targetList, Long parentId){
        for(T currentEntity: sourceList){
            if(currentEntity.getParent() != null && currentEntity.getParent().getId().equals(parentId)){
                targetList.add(currentEntity);

                // 判断是否是父节点、继续追加子节点
                if(currentEntity.getIsParent()){
                    for(T childEntity :sourceList){
                        if(childEntity.getParent() != null && currentEntity.getId().equals(childEntity.getParent().getId())){
                            sortList(sourceList,targetList,currentEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据parentId取得列表
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id);

    /**
     * 跳转到表单页
     *
     * @return
     */
    public abstract String form(T entity);

    /**
     * 删除
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);


}
