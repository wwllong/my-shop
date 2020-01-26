package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.domain.entity.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wenwl
 * @className TbUserDao
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Repository
public interface TbUserDao {

    /**
     * 查询全部用户信息
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 插入一条用户信息
     * @return
     */
    public Integer insert(TbUser tbUser);

    /**
     * 删除用户Id
     */
    public Integer delete(TbUser tbUser);

    /**
     * 查询单个对象
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

}
