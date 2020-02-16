package com.wenwl.my.shop.domain.entity;

import com.wenwl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

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
public class TbUser extends BaseEntity implements Serializable {

    private String username;
    private String password;
    private String phone;
    private String email;

    private String rememberMe;

}
