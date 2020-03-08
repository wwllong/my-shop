package com.wenwl.my.shop.domain.entity;

import com.wenwl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author wenwl
 * @className TbContent
 * @dsecription 内容
 * @data 2020/2/20 0:06
 * @vserion 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TbContent extends BaseEntity {
    /**
     * 标题
     */
    @Length(min = 1, max = 20, message = "标题长度介于 1 - 20 个字符之间")
    private String title;
    /**
     * 子标题
     */
    @Length(min = 1, max = 20, message = "子标题长度介于 1 - 20 个字符之间")
    private String subTitle;
    /**
     * 标题描述
     */
    @Length(min = 1, max = 50, message = "标题描述长度介于 1 - 50 个字符之间")
    private String titleDesc;
    /**
     * 图片绝对路径
     */
    private String url;
    /**
     * 图片1
     */
    private String pic;
    /**
     * 图片2
     */
    private String pic2;
    /**
     * 内容
     */
    @Length(min = 1, message = "内容不能为空")
    private String content;

    /**
     * 父级类目对象
     */
    @NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;
}
