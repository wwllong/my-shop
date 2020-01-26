package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.admin.dao.TbUserDao;
import com.wenwl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wenwl
 * @className TbUserServiceImpl
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    /**
     * 查询全部用户信息
     *
     * @return
     */
    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    /**
     * 插入一条用户信息
     *
     * @param tbUser
     * @return
     */
    @Override
    public Integer insert(TbUser tbUser) {
        return tbUserDao.insert(tbUser);
    }

    /**
     * 删除用户
     *
     * @param tbUser
     * @return
     */
    @Override
    public Integer delete(TbUser tbUser) {
        return tbUserDao.delete(tbUser);
    }

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    /**
     * 模糊查询
     *
     * @param username
     * @return
     */
    @Override
    public List<TbUser> selectByName(String username) {
        return tbUserDao.selectByName(username);
    }


}
