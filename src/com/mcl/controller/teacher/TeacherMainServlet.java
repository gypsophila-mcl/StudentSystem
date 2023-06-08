package com.mcl.controller.teacher;

import com.mcl.entity.Classes;
import com.mcl.entity.PageInfo;
import com.mcl.entity.Teacher;
import com.mcl.service.TeacherService;
import com.mcl.service.impl.TeacherServiceImpl;

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
@WebServlet("/teacherMain")
public class TeacherMainServlet extends HttpServlet {
    private TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        int currentpage = Integer.parseInt((currentPage == null || currentPage.equals(""))?"1":currentPage);
        int pagesize = Integer.parseInt((pageSize == null || pageSize.equals(""))?"8":pageSize);
        PageInfo<Teacher> page = teacherService.getPage(currentpage, pagesize);
        page.setManageType(2);

//        //打印
//        for (Teacher teacher : page.getList()) {
//            System.out.println(teacher);
//        }

        req.setAttribute("pageInfo",page);
        req.getRequestDispatcher("/main.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
