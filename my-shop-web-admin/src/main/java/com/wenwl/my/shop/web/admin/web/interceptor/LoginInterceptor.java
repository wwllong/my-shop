package com.wenwl.my.shop.web.admin.web.interceptor;

import com.wenwl.my.shop.commons.constants.ConstantUtils;
import com.wenwl.my.shop.domain.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wenwl
 * @className LoginInterceptor
 * @dsecription 登陆拦截器
 * @data 2020/1/13
 * @vserion 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        User user = (User)httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);

        // 判断用户有无登陆
        if(user == null){
            httpServletResponse.sendRedirect("/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
