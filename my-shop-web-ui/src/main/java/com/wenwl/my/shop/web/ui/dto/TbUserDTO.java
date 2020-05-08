package com.wenwl.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wenwl
 * @version 1.0.0
 * @className TbUserDTO
 * @description 用户DTO
 * @date 2020/5/6 23:59
 */
@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String verification;
}
