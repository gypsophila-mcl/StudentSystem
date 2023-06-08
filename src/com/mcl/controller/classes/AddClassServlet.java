package com.mcl.controller.classes;

import com.mcl.entity.Classes;
import com.mcl.service.ClassesService;
import com.mcl.service.impl.ClassesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 添加班级类
 */
@WebServlet("/addClass")
public class AddClassServlet extends HttpServlet {
    private ClassesService classesService = new ClassesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String className = req.getParameter("className");
        String stdNum = req.getParameter("stdNum");
        String manager = req.getParameter("manager");
        String teacherNo = req.getParameter("teacherNo");
        int stdnum = Integer.parseInt(stdNum);
        Classes classes = new Classes(className, stdnum, manager,teacherNo);

        String addMsg = null;
        try {
            boolean isAdd = classesService.addClass(classes);
            //保存数据
            addMsg = isAdd?"添加成功":"添加失败";
        } catch (Exception e) {
            e.printStackTrace();
            addMsg = "班级名称重复，添加失败";
        }finally {
            req.setAttribute("addMsg",addMsg);
            //转发
            req.getRequestDispatcher("/clsMain").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
