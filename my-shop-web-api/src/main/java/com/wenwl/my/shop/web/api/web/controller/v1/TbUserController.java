package com.wenwl.my.shop.web.api.web.controller.v1;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.api.service.TbUserService;
import com.wenwl.my.shop.web.api.web.dto.TbUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenwl
 * @className TbUserController
 * @dsecription 会员管理
 * @data 2020/4/8 22:37
 * @vserion 1.0.0
 */
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 登录
     *
     * @param tbUser
     * @return
     */
    @PostMapping("login")
    public BaseResult login(TbUser tbUser) {
        TbUser user = tbUserService.login(tbUser);
        if (user == null) {
            return BaseResult.fail("账号或密码错误");
        } else {
            TbUserDTO dto = new TbUserDTO();
            BeanUtils.copyProperties(user, dto);
            return BaseResult.success("成功", dto);
        }
    }

}
