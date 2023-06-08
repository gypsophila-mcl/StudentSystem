package com.mcl.controller.student;

import com.mcl.entity.PageInfo;
import com.mcl.entity.Student;
import com.mcl.service.StudentService;
import com.mcl.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
@WebServlet("/stdMain")
public class StdMainServlet extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理请求：准备首页需要的数据（分页学生信息）
        //获取当前分页信息
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        String manageType = req.getParameter("manageType");

        int currentpage = Integer.parseInt((currentPage == null || currentPage.equals(""))?"1":currentPage);
        int pagesize = Integer.parseInt((pageSize == null || pageSize.equals(""))?"10":pageSize);
        int managetype = Integer.parseInt((manageType == null || manageType.equals("")) ? "0" : manageType);

        PageInfo<Student> page = studentService.getPage(currentpage, pagesize);
        page.setManageType(managetype);

//        String isSearch = req.getParameter("isSearch");
//        if (isSearch.equals("true")){
//
//        }

        //保存分页数据
        req.setAttribute("pageInfo",page);

        req.getRequestDispatcher("/main.jsp").forward(req,resp);
        //保存数据(把数据传递给下个页面)
        // 1 使用 request 保存数据
        //--------------测试代码--------------
//        for (Student student : page.getList()) {
//            System.out.println(student);
//        }
//        req.setAttribute("name","小明");
//        //保存 Student 对象
//        Student student = new Student(1, "110", "abc", "男", new Date(), "11111", "123123213123");
//        //保存到 request 中
//        req.setAttribute("std",student);
        //--------------测试代码--------------
        // 2 跳转到学生首页 (转发)
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
