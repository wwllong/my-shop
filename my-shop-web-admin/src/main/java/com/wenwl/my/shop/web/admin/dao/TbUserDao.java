package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.domain.entity.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wenwl
 * @className TbUserDao
 * @dsecription 用户数据访问对象
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Repository
public interface TbUserDao {

    /**
     * 查询所有列表
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
     * 根据邮箱查询
     * @param email
     * @return
     */
    public TbUser getByEmail(String email);

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
    public List<TbUser> page(Map<String,Object> params);

    /**
     * 统计总记录数
     * @return
     */
    public int count(TbUser tbUser);
}
