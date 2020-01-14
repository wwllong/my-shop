package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.domain.entity.User;

/**
 * @author wenwl
 * @className UserDao
 * @data 2019/12/23
 * @vserion 1.0.0
 */
public interface UserDao {

    /**
     * 根据邮箱和密码获取用户信息
     * @param email 邮箱
     * @param password 密码
     * @return 用户
     */
    public User getUser(String email, String password);

}
