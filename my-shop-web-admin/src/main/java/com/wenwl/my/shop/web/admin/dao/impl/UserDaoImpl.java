package com.wenwl.my.shop.web.admin.dao.impl;

import com.wenwl.my.shop.web.admin.dao.UserDao;
import com.wenwl.my.shop.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author wenwl
 * @className UserDaoImpl
 * @data 2019/12/23
 * @vserion 1.0.0
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User getUser(String email, String password) {
        logger.debug("调用getUser(),email:{} password {}",email,password);

        User user = null;

        if("admin@163.com".equals(email)){
            if("admin".equals(password)){
                user = new User();
                user.setUsername("wenwl");
                logger.info("成功获取 {} 的用户信息",user.getUsername());
            }
        }else{
            logger.warn("获取 {} 的用户信息失败",email);
        }

        return user;
    }

}
