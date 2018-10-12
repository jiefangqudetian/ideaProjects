package com.kaishengit.interception;

import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //请求的URL
        String url = request.getRequestURI();
        System.out.println(url);
        if (url.startsWith("/static/") || url.equals("favicon.ico") || url.startsWith("/login")){
            return true;
        }
        //首页 "" /
        if ("".equals(url)||"/".equals(url)){
            return true;
        }


        /*HttpSession session = request.getSession();
        if (session.getAttribute("curr_acount") == null){
            response.sendRedirect("/login");
        }*/
        return true;
    }
}
