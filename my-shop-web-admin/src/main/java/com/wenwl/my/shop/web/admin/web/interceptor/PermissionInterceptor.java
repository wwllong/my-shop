package com.wenwl.my.shop.web.admin.web.interceptor;

import com.wenwl.my.shop.commons.constants.ConstantUtils;
import com.wenwl.my.shop.domain.entity.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wenwl
 * @className PermissionInterceptor
 * @dsecription 鉴权许可拦截器
 * @data 2020/1/13
 * @vserion 1.0.0
 */
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        // 请求来自登录页
        if(modelAndView.getViewName().endsWith("login")){
            TbUser user = (TbUser)httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if(user != null){
                //直接重定向到首页
                httpServletResponse.sendRedirect("/main");
            }
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
