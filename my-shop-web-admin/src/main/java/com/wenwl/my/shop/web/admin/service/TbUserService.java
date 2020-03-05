package com.wenwl.my.shop.web.admin.service;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.domain.entity.TbUser;

import java.util.List;
import java.util.Map;

/**
 * @author wenwl
 * @className TbUserService
 * @data 2020/1/16 0016
 * @vserion 1.0.0
 */
public interface TbUserService {

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
    public int delete(TbUser tbUser);

    /**
     * 查询用户
     * @param id
     * @return
     */
    public TbUser getById(Long id);

    /**
     * 登陆
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    long batchDelete(String[] ids);

    /**
     * 分页查询
     * @param params
     * @return
     */
    public PageInfo<TbUser> page(Map<String,Object> params);

    /**
     * 统计总记录数
     * @return
     */
    public int count(TbUser tbUser);

}
