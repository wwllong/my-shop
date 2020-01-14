package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.web.admin.dao.UserDao;
import com.wenwl.my.shop.domain.entity.User;
import com.wenwl.my.shop.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenwl
 * @className UserServiceImpl
 * @data 2019/12/23
 * @vserion 1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 登陆
     *
     * @param email    邮箱
     * @param password 密码
     * @return 用户
     */
    public User login(String email, String password) {
        return userDao.getUser(email,password);
    }

}
