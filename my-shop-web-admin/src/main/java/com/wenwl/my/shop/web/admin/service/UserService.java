package com.wenwl.my.shop.web.admin.service;

import com.wenwl.my.shop.domain.entity.User;

/**
 * @author wenwl
 * @className UserService
 * @data 2019/12/23 0023
 * @vserion 1.0.0
 */
public interface UserService {
    /**
     * 登陆
     * @param email 邮箱
     * @param password 密码
     * @return 用户
     */
    public User login(String email, String password);
}
