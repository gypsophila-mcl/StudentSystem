package com.mcl.controller.classes;

import com.mcl.entity.Classes;
import com.mcl.entity.PageInfo;
import com.mcl.service.impl.ClassesServiceImpl;

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
@WebServlet("/clsMain")
public class ClsMainServlet extends HttpServlet {
    ClassesServiceImpl classesService = new ClassesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取当前页信息
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");
        int currentpage = Integer.parseInt((currentPage == null || currentPage.equals(""))?"1":currentPage);
        int pagesize = Integer.parseInt((pageSize == null || pageSize.equals(""))?"8":pageSize);
        PageInfo<Classes> page = classesService.getPage(currentpage, pagesize);
        page.setManageType(1);

//        System.out.println(managetype);
//        //打印班级信息
//        for (Classes classes : page.getList()) {
//            System.out.println(classes);
//        }

        //保存分页数据
        req.setAttribute("pageInfo", page);

        //转发
        req.getRequestDispatcher("/main.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
