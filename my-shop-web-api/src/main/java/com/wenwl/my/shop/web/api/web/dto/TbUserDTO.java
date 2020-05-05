package com.wenwl.my.shop.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wenwl
 * @className TbContentDTO
 * @dsecription 会员数据传输对象
 * @data 2020/3/29 18:17
 * @vserion 1.0.0
 */
@Data
public class TbUserDTO implements Serializable {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
