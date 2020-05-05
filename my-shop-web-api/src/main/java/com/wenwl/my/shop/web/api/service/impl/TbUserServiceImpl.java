package com.wenwl.my.shop.web.api.service.impl;

import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.api.dao.TbUserDao;
import com.wenwl.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author wenwl
 * @className TbUserServiceImpl
 * @data 2020/4/8 22:33
 * @vserion 1.0.0
 */

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    /**
     * 登录
     * @param tbUser
     * @return
     */
    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);

        if (user != null) {
            // 将明文密码加密
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (password.equals(user.getPassword())) {
                return user;
            }
        }

        return null;
    }
}
