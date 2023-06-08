package com.mcl.controller.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcl.entity.Classes;
import com.mcl.service.ClassesService;
import com.mcl.service.impl.ClassesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: TODO
 */
@WebServlet("/delClassById")
public class DelClassByIdServlet extends HttpServlet {
    private ClassesService classesService = new ClassesServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取页面信息（id）
        int clsId = Integer.parseInt(req.getParameter("clsId"));

        //数据持久化
        String delMsg = null;
        try {
            boolean isDel = classesService.delClassById(clsId);
            delMsg = isDel?"删除成功":"删除失败";
        } catch (Exception e) {
            e.printStackTrace();
            delMsg = "班级中还有学生，无法删除";
        }finally {
            PrintWriter writer = resp.getWriter();
            writer.println(delMsg);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
