package com.wenwl.my.shop.commons.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wenwl
 * @className BaseEntity
 * @dsecription 实体基类
 * @data 2020/2/16
 * @vserion 1.0.0
 */
public class BaseEntity implements Serializable {

    private Long id;
    private Date created;
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
