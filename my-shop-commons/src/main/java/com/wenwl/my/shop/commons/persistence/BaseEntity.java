package com.wenwl.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wenwl
 * @className BaseEntity
 * @dsecription 实体基类
 * @data 2020/2/16
 * @vserion 1.0.0
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -6153861510214010832L;

    private Long id;
    private Date created;
    private Date updated;

}
