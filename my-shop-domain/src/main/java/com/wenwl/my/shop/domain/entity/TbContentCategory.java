package com.wenwl.my.shop.domain.entity;

import com.wenwl.my.shop.commons.persistence.BaseEntity;
import com.wenwl.my.shop.commons.persistence.BaseTreeEntity;
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
@EqualsAndHashCode(callSuper = true)
public class TbContentCategory extends BaseTreeEntity<TbContentCategory> {

    @Length(min = 2, max = 6, message = "分类名称长度必须介于 2 和 6 之间")
    private String name;
    /**
     * 状态。可选值:1(正常),2(删除)
     */
    private Integer status;

    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

}
