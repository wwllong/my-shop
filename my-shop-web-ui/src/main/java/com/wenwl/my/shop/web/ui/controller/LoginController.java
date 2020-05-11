package com.wenwl.my.shop.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.web.ui.api.UsersApi;
import com.wenwl.my.shop.web.ui.constant.SystemConstants;
import com.wenwl.my.shop.web.ui.dto.TbUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wenwl
 * @className LoginController
 * @dsecription 登陆控制器
 * @data 2020/4/9 23:37
 * @vserion 1.0.0
 */
@Controller
public class LoginController {

    /**
     * 跳转登录页
     * @return
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 登录校验
     * @param tbUser
     * @param model
     * @param request
     * @return
     */
    @PostMapping("login")
    public String login(TbUserDTO tbUser, Model model, HttpServletRequest request) throws Exception{
        // 验证码验证失败
        if (!checkCaptcha(tbUser, request)) {
            model.addAttribute("baseResult", BaseResult.fail("验证码输入错误，请重新输入"));
            return "login";
        }

        TbUserDTO user = UsersApi.login(tbUser);
        // 登录失败
        if (user == null) {
            model.addAttribute("baseResult", BaseResult.fail("用户名或密码错误，请重新输入！"));
            return "login";
        }
        // 登录成功
        else {
//            emailSendUtils.send("用户登录", String.format("用户 【%s】 登录 MyShop", user.getUsername()), "lusifer@yeah.net");
            // 将会员信息放入 Session
            request.getSession().setAttribute(SystemConstants.SESSION_USER_KEY, user);
            return "redirect:/index";
        }
    }

    /**
     * 校验验证码
     * @param tbUser
     * @param request
     * @return
     */
    private boolean checkCaptcha(TbUserDTO tbUser, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(captcha, tbUser.getCaptcha())) {
            return true;
        }
        return false;
    }

    /**
     * 注销
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }

}
