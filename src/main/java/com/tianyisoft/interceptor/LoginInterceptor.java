package com.tianyisoft.interceptor;

import com.tianyisoft.po.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tianyi
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        if (user != null) {
            return true;
        }
        request.setAttribute("msg", "你还没有登录，请先登录!");
        request.getRequestDispatcher("login").forward(request, response);
        return false;
    }
}
