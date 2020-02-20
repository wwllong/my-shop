package com.wenwl.my.shop.domain.entity;

import com.wenwl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * @author wenwl
 * @className TbContent
 * @dsecription 内容
 * @data 2020/2/20 0:06
 * @vserion 1.0.0
 */
@Data
public class TbContent extends BaseEntity {
    /**
     * 内容分类Id
     */
    private Long categoryId;
    /**
     * 标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 标题描述
     */
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
    private String content;
}
