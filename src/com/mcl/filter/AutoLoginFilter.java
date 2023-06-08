package com.mcl.filter;

import com.mcl.entity.User;
import com.mcl.service.UserService;
import com.mcl.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 拦截器（拦截请求）
 */
@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    private UserService userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @param servletRequest:
     * @param servletResponse:
     * @param filterChain:
     * @description 过滤器要做的事情（自动登录）
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //自动登录
        //(1)获取浏览器中 cookie 数据
        Cookie[] cookies = request.getCookies();
        //(2)遍历 cookie 数据
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                //(3)获取 cookie 数据 key:value
                String key = cookie.getName();
                String value = cookie.getValue();
                //(4)判断是不是与自动登录相关的 cookie 数据 key-->autoLogin value-->账号:密码
                if (key.equals("autoLogin") && value!=null){
                    //(5)这就是自动登录相关的 cookie，获取用户信息
                    String[] split = value.split("=");
                    String userName = split[0];
                    String password = split[1];
                    //(6)判断账号密码是否正确
                    User user = new User(userName, password);
                    boolean login = userService.login(user);
                    if (login){
                        //(7)账号密码正确，保存到 session 中
                        HttpSession session = request.getSession();
                        session.setAttribute("user",user);
                        //(8)如果当前拦截的是登录页面，直接跳转到首页
//                        System.out.println(request.getRequestURI());
                        if (request.getRequestURI().equals("/stuSys/") || request.getRequestURI().equals("/stuSys/index.jsp")){
                            request.getRequestDispatcher("/stdMain").forward(request,response);
                            return;
                        }
                    }
                }
            }
        }

        //(9)对请求进行放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
