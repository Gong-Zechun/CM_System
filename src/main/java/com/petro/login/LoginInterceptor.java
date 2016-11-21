package com.petro.login;

import com.petro.UnLoginException;
import com.petro.controller.PetroUserController;
import com.petro.pojo.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-18 17:27
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        if ("GET".equalsIgnoreCase(request.getMethod())) {
//            RequestUtil.saveRequest();
//        }
        if("GET".equalsIgnoreCase(request.getMethod())) {
            log.info("==============执行顺序: 1、preHandle================");
            String requestUri = request.getRequestURI();
            String contextPath = request.getContextPath();
            String url = requestUri.substring(contextPath.length());
            if ("/petro/login".equals(url)) {
                return true;
            } else {
                String username = (String) request.getSession().getAttribute("userName");
                if (username == null) {
                    log.info("Interceptor：跳转到login页面！");
                    //request.getRequestDispatcher("/petro/login").forward(request, response);
                    response.sendRedirect(request.getContextPath() + "/petro/login");
                    return false;
                } else {
                    return true;
                }
            }
        }else if("POST".equalsIgnoreCase(request.getMethod())){
            return true;
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
