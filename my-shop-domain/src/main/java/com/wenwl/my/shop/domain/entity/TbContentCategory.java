package com.wenwl.my.shop.domain.entity;

import com.wenwl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author wenwl
 * @className TbContentCategory
 * @dsecription 内容分类
 * @data 2020/2/16
 * @vserion 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbContentCategory extends BaseEntity {

    @Length(min = 2, max = 6, message = "分类名称长度必须介于 2 和 6 之间")
    private String name;
    /**
     * 状态。可选值:1(正常),2(删除)
     */
    private Integer status;

    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    /**
     * 该类目是否为父类目，1为true，0为false，默认1
     */
    private Boolean isParent;

    @NotNull(message = "父节点不能为空")
    private TbContentCategory parent;
}
