package com.wenwl.my.shop.commons.dto;

import com.wenwl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author wenwl
 * @className PageInfo
 * @dsecription 分页数据传输对象
 * @data 2020/2/16
 * @vserion 1.0.0
 */
@Data
public class PageInfo<T extends BaseEntity> {

    /**
     * DT（datatable）绘制计数器
     */
    private int draw;
    /**
     * 数据库里总共记录数
     */
    private int recordsTotal;
    /**
     * 过滤后的记录数
     */
    private int recordsFiltered;
    /**
     * 表中中需要显示的数据
     */
    private List<T> data;
    /**
     * 错误提示
     */
    private String error;

}
