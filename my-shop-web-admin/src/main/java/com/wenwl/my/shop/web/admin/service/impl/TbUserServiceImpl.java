package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.utils.BeanValidator;
import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.admin.commons.framework.AbstractServiceImpl;
import com.wenwl.my.shop.web.admin.dao.TbUserDao;
import com.wenwl.my.shop.web.admin.service.TbUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author wenwl
 * @className TbUserServiceImpl
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Service
public class TbUserServiceImpl extends AbstractServiceImpl<TbUserDao, TbUser> implements TbUserService {

    /**
     * 保存用户信息
     *
     * @param tbUser
     * @return
     */
    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        // 验证不通过
        if(validator != null){
            //TODO 密码单独校验
            return BaseResult.fail(validator);
        }
        // 验证通过
        else{
            tbUser.setUpdated(new Date());
            //新增用户
            if(tbUser.getId() == null){
                // 密码加密处理
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                baseDao.insert(tbUser);
            }
            //编辑用户
            else{
                // TODO 密码问题
                baseDao.update(tbUser);
            }
            return BaseResult.success("保存信息成功");
        }
    }

    /**
     * 登陆
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = baseDao.getByEmail(email);
        if(tbUser != null){
            // 明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            // 判断密码是否匹配
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }

        }
        return null;
    }

}
