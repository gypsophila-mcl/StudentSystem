package com.mcl.controller.user;

import com.mcl.entity.User;
import com.mcl.service.UserService;
import com.mcl.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 登录请求类
 */
@WebServlet("/login") //指定当前 Servlet 可以处理那些请求
public class LoginServlet extends HttpServlet {
    //创建 UserService 接口对象
    private UserService userService = new UserServiceImpl();
    // Servlet:处理用户请求，常用API：
    //      HttpServletRequest:处理用户请求(数据)
    //          getParameter(String var1);  -->获取用户请求数据
    //      HttpServletResponse:返回响应(处理结果)给用户
    //          getRequestDispatcher(String var1);  -->将请求进行转发，参数(var1)为转发路径(Servlet映射地址也可以是页面)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果用户发送的是 get 请求，则会进入这个方法进行处理
        // 实现登录功能
        // 1 获取用户账号和密码(既获取页面数据)
        // 1.1 获取账号
        String username = req.getParameter("username");
        // 1.2 获取密码
        String password = req.getParameter("password");
        // 2 根据账号密码从数据库查询数据
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //测试
//        System.out.println(user);
        boolean login = userService.login(user);
        if (login){
            // 3 如果查到数据，说明账号密码正确(登陆成功)
            // 3.1 进入学生详情页
            //登录成功，把用户信息保存在 session 中
            //(1)拿 session 对象
            HttpSession session = req.getSession();//Tomcat 创建的session对象，并拥有唯一id
            //获取唯一id
//            String id = session.getId();
//            System.out.println("session=" + id);
            //(2)保存数据 key:value 键值对方式
            session.setAttribute("userInfo",user);
            // 从 session 中获取数据
             User u = (User)session.getAttribute("userInfo");

            //获取自动登陆参数
            String rememberMe = req.getParameter("rememberMe");
            if (rememberMe!=null && rememberMe.equals("true")){
                //需要实现自动登录功能
                //(1)创建 Cookie 对象，保存用户数据
                //拼一下账号密码 value= userName:password
                Cookie cookie = new Cookie("autoLogin", username+"="+password);
                //设置有效时间
                cookie.setMaxAge(60*60*24*3); //单位是s,保存时间为3天
                //设置有效路径
                cookie.setPath(req.getContextPath());
                //(2)把 Cookie 数据返回
                resp.addCookie(cookie);
            }

            req.getRequestDispatcher("/stdMain").forward(req,resp);
        }
        else {
            // 4 查不到数据(登陆失败)
            // 4.1 返回登录页面(转发到登录页)
            // 4.2 保存登陆失败信息
            req.setAttribute("errMsg","账号密码有误，请重新输入");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果用户发送的是 post 请求，则会进入这个方法进行处理
        doGet(req,resp);
    }
}
