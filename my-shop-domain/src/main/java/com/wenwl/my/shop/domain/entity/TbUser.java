package com.wenwl.my.shop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wenwl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wenwl
 * @className TbUser
 * @dsecription 用户实体
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbUser extends BaseEntity{

    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;

    private String rememberMe;

}
