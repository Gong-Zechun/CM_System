package com.petro.login;

import com.petro.UnLoginException;
import com.petro.pojo.AdminUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-18 17:27
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //创建session
        HttpSession session = request.getSession();

        //无需登录，允许访问的地址
        String[] allowUrls =new String[]{"/login", "/main"};

        //获取请求地址
        String url = request.getRequestURL().toString();

        //获得session中的用户
        AdminUser adminUser =(AdminUser) session.getAttribute("usrName");


        for (String strUrl : allowUrls) {
            if(url.contains(strUrl)) {
                return true;
            }
        }

        if(adminUser == null) {
            //throw new UnLoginException("您尚未登录！");
            response.sendRedirect(request.getContextPath()+"/petro/login");
        }
        //重定向
        //response.sendRedirect(request.getContextPath()+"/login");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
