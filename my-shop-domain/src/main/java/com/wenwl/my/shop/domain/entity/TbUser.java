package com.wenwl.my.shop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wenwl.my.shop.commons.persistence.BaseEntity;
import com.wenwl.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
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
@EqualsAndHashCode(callSuper = true)
public class TbUser extends BaseEntity{

    /**
     * 用户名
     */
    @Length(min = 6, max = 20, message = "用户名长度必须介于 6 和 20 之间")
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码长度必须介于 6 和 20 之间")
    private String password;
    /**
     * 电话
     */
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;
    /**
     * 邮箱
     */
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;

    /**
     * 登陆（记住我）
     */
    private String rememberMe;

}
