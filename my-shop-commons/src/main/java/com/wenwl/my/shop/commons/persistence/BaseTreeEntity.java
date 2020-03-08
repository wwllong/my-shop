package com.wenwl.my.shop.commons.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author wenwl
 * @className BaseTreeEntity
 * @dsecription 树实体基类
 * @data 2020/3/8 21:04
 * @vserion 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTreeEntity<T extends BaseEntity> extends BaseEntity  {

    @NotNull(message = "父节点不能为空")
    private T parent;

    /**
     * 该类目是否为父类目，1为true，0为false，默认1
     */
    private Boolean isParent;

}
