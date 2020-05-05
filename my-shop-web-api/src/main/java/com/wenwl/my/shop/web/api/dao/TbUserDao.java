package com.wenwl.my.shop.web.api.dao;

import com.wenwl.my.shop.domain.entity.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @author wenwl
 * @className TbUserDao
 * @dsecription 会员管理
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Repository
public interface TbUserDao{

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);


}
