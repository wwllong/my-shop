package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.commons.constants.ConstantUtils;
import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.admin.commons.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.wenwl.my.shop.web.admin.service.UserService;

/**
 * @author wenwl
 * @className LoginController
 * @dsecription 登陆控制器
 * @data 2020/1/13
 * @vserion 1.0.0
 */
@Controller
public class LoginController {

//    @Autowired
//    private UserService userService;

    @GetMapping(value = {"", "login"})
    public String login(HttpServletRequest req) {

        String userInfo = CookieUtil.getCookieValue(req, ConstantUtils.COOKIE_NAME_USER_INFO);
        if(StringUtils.isNotBlank(userInfo)){
            String[] userInfoArr = userInfo.split(":");
            req.setAttribute("email",userInfoArr[0]);
            req.setAttribute("password",userInfoArr[1]);
            req.setAttribute("isRemember",true);
        }
        return "login";
    }

    @PostMapping(value = "login")
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest req, HttpServletResponse resp) {

//        User user = userService.login(email, password);
        TbUser user = null;
        boolean isRememberMe = req.getParameter("isRemember") != null;

        //用户选择不记住
        if(!isRememberMe){
            CookieUtil.deleteCookie(req,resp,ConstantUtils.COOKIE_NAME_USER_INFO);
        }

        //登陆成功
        if(user != null){
            // 登录信息放入会话
            req.getSession().setAttribute(ConstantUtils.SESSION_USER,user);
            //存储用户信息（一周）
            if(isRememberMe){
                CookieUtil.setCookie(req,resp,ConstantUtils.COOKIE_NAME_USER_INFO,String.format("%s:%s",email,password), 7 * 24 * 3600);
            }
            return "redirect:/main";
        }
        //登陆失败
        else{
            req.setAttribute("message","用户名或密码错误");
            return login(req);
        }

    }

    @GetMapping(value = "logout")
    public String logout(HttpServletRequest req){
        req.getSession().invalidate();
        return login(req);
    }

}
