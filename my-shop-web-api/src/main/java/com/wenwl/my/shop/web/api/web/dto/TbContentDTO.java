package com.wenwl.my.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wenwl
 * @className TbContentDTO
 * @dsecription 内容数据传输对象
 * @data 2020/3/29 18:17
 * @vserion 1.0.0
 */
@Data
public class TbContentDTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
