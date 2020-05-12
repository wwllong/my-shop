package com.wenwl.my.shop.web.api.service;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbUser;

/**
 * @author wenwl
 * @className TbUserService
 * @data 2020/1/16 
 * @vserion 1.0.0
 */
public interface TbUserService {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);

    /**
     * 注册
     * @param tbUser
     * @return
     */
    BaseResult register(TbUser tbUser);

    /**
     * 校验是否重复注册
     * @param tbUser
     * @return
     */
    boolean checkRegister(TbUser tbUser);
}
