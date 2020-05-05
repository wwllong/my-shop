package com.wenwl.my.shop.web.api.service;

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

}
