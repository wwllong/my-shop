package com.wenwl.my.shop.web.admin.service;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbUser;

import java.util.List;

/**
 * @author wenwl
 * @className TbUserService
 * @data 2020/1/16 0016
 * @vserion 1.0.0
 */
public interface TbUserService {

    /**
     * 查询全部用户信息
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 保存用户信息
     * @return
     */
    public BaseResult save(TbUser tbUser);

    /**
     * 删除用户
     * @param tbUser
     * @return
     */
    public Integer delete(TbUser tbUser);

    /**
     * 查询用户
     * @param id
     * @return
     */
    public TbUser getById(Long id);


    /**
     * 模糊查询
     * @param username
     * @return
     */
    public List<TbUser> selectByName(String username);

    /**
     * 登陆
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);
}
