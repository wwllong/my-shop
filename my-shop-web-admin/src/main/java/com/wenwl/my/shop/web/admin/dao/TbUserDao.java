package com.wenwl.my.shop.web.admin.dao;

import com.wenwl.my.shop.commons.framework.BaseDao;
import com.wenwl.my.shop.domain.entity.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @author wenwl
 * @className TbUserDao
 * @dsecription 用户数据访问对象
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    public TbUser getByEmail(String email);

}
