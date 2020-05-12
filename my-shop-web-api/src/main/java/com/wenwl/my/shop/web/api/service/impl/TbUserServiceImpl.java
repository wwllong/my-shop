package com.wenwl.my.shop.web.api.service.impl;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.utils.BeanValidator;
import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.api.dao.TbUserDao;
import com.wenwl.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

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

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);

        if (user != null) {
            // 将明文密码加密 ，判断是否相等
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (password.equals(user.getPassword())) {
                return user;
            }
        }

        return null;
    }

    @Override
    public BaseResult register(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        // 验证不通过
        if(validator != null){
            return BaseResult.fail(validator);
        }
        // 验证通过，注册会员
        else{
            // 密码加密处理
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            tbUserDao.insert(tbUser);
            return BaseResult.success("注册会员成功！");
        }
    }

    @Override
    public boolean checkRegister(TbUser tbUser) {
        return tbUserDao.count(tbUser) > 0;
    }
}
