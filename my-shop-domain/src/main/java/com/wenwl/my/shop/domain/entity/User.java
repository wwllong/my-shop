package com.wenwl.my.shop.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wenwl
 * @className User
 * @dsecription 用户实体
 * @data 2019/12/23
 * @vserion 1.0.0
 */
@Data
public class User implements Serializable {

    private String username;
    private String password;
    private String email;
    private String rememberMe;

}
