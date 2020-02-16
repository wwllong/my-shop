package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.domain.entity.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public int insert(TbUser tbUser);

    /**
     * 删除用户Id
     */
    public int delete(TbUser tbUser);

    /**
     * 查询单个对象
     * @param id
     * @return
     */
    public TbUser getById(Long id);

    /**
     * 更新
     * @param tbUser
     * @return
     */
    public int update(TbUser tbUser);

    /**
     * 模糊查询
     * @param username
     * @return
     */
    public List<TbUser> selectByName(String username);

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    public TbUser getByEmail(String email);

    /**
     * 搜索
     */
    public List<TbUser> search(TbUser tbUser);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    long deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params
     * @return
     */
    public List<TbUser> page(Map<String,Object> params);

    /**
     * 统计总记录数
     * @return
     */
    public int count();
}
