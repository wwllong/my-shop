package com.wenwl.my.shop.web.admin.service;

import com.wenwl.my.shop.commons.framework.BaseService;
import com.wenwl.my.shop.domain.entity.TbUser;

/**
 * @author wenwl
 * @className TbUserService
 * @data 2020/1/16 0016
 * @vserion 1.0.0
 */
public interface TbUserService extends BaseService<TbUser> {

    /**
     * 登陆
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);

}
