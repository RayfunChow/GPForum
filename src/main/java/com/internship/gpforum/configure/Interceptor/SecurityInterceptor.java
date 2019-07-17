package com.internship.gpforum.configure.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Object userInfo = request.getSession().getAttribute("User");
        if(userInfo==null) {
            try {
                request.getRequestDispatcher("login").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return false;
        }else
            return true;
    }
}
