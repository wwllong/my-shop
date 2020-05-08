package com.wenwl.my.shop.web.ui.interceptor;

import com.wenwl.my.shop.web.ui.constant.SystemConstants;
import com.wenwl.my.shop.web.ui.dto.TbUserDTO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wenwl
 * @version 1.0.0
 * @className LoginInterceptor
 * @description 登录拦截器
 * @date 2020/5/8 15:12
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUserDTO tbUserDTO = (TbUserDTO) httpServletRequest.getSession().getAttribute(SystemConstants.SESSION_USER_KEY);
        // 未登录状态
        if (tbUserDTO == null) {
            return true;
        }
        // 已登录状态
        else {
            httpServletResponse.sendRedirect("/index");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
