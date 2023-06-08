package com.mcl.controller.user;

import com.mcl.entity.User;
import com.mcl.service.UserService;
import com.mcl.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean isRegister = false;
        String errMsg = null;
        try {
            isRegister = userService.register(user);

            errMsg = isRegister?"注册成功":"注册失败";
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = "用户已存在";
        }finally {
            req.setAttribute("errMsg",errMsg);
            if (isRegister){
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }else {
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
