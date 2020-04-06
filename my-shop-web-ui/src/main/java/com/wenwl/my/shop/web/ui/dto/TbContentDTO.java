package com.wenwl.my.shop.web.ui.dto;

import lombok.Data;

/**
 * @author wenwl
 * @className TbContentDTO
 * @dsecription 内容数据传输对象
 * @data 2020/4/6 15:59
 * @vserion 1.0.0
 */
@Data
public class TbContentDTO {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
